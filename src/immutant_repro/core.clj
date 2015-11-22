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
    (reset! web-server-handle
      (web/run handler
        :http2? true
        :keystore "certs/server.keystore"
        :key-password "password"
        :truststore "certs/server.truststore"
        :trust-password "password"
        :port 8080
        :ssl-port 8443)))

  (defn stop!
    "Stops the application."
    []

    (when (web/stop @web-server-handle)
      (reset! web-server-handle nil)))

  (defn -main
    "The main function."
    []

    (start!))
