(ns immutant-repro.core
  (:require [immutant.web :as web]
            [immutant.web.undertow :as undertow]
            [less.awful.ssl :as less-awful-ssl])
  (:import [io.undertow UndertowOptions])
  (:gen-class))

;; Defines.

  (defonce web-server-handle
    ; "Atom which holds the handle for the web server."

    (atom nil))

;; Repro

  (defn handler
    "Simple Ring handler that shows the last check status value."
    [request]

    {:status  200
     :headers {"Content-Type"   "text/html"}
     :body    "Hello SSL!"})


  (defn start!
    "Starts the application."
    []

    (let [port 8080
          ssl-port 8443
          use-http2? true
          ssl-context (less-awful-ssl/ssl-context "certs/server.pkcs8" "certs/server.crt" "certs/rootCA.pem")
          configuration (undertow/options {:host "0.0.0.0"
                                           :keystore "certs/server.keystore"
                                           :key-password "password"
                                           :truststore "certs/server.truststore"
                                           :trust-password "password"
                                           :port port
                                           :ssl-port ssl-port})
          configuration (if use-http2?
                          (update configuration :configuration
                            #(doto %
                               (.setServerOption UndertowOptions/ENABLE_HTTP2 true)
                               (.setServerOption UndertowOptions/ENABLE_SPDY true)))
                          configuration)]
      (reset! web-server-handle (web/run handler configuration))))

  (defn stop!
    "Stops the application."
    []

    (when (web/stop @web-server-handle)
      (reset! web-server-handle nil)))

  (defn -main
    "The main function."
    []

    (start!))
