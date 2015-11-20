(defproject immutant-repro "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.immutant/web "2.1.1"]
                 [less-awful-ssl "1.0.0"]]
  :plugins [[info.sunng/lein-bootclasspath-deps "0.2.0"]]
  :boot-dependencies [[org.mortbay.jetty.alpn/alpn-boot "8.1.5.v20150921" :prepend true]]
  :main immutant-repro.core
  :profiles {:uberjar {:aot :all}})
