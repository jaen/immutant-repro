(defproject immutant-repro "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]

                 [org.immutant/web "2.1.1"]
                 [org.mortbay.jetty.alpn/alpn-boot "8.1.5.v20150921"]
                 [less-awful-ssl "1.0.0"]]
  :main immutant-repro.core)
