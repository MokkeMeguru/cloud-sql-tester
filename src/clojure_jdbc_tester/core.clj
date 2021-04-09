(ns clojure-jdbc-tester.core
  (:gen-class)
  (:require [environ.core :refer [env]]
            [clojure-jdbc-tester.proxy :as cjt-proxy]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [connection-method (env :connection-method)]
    (cond
      (= connection-method "proxy") (cjt-proxy/-main)
      (= connection-method "socket-factory") (println "oh")
      :else (throw (ex-info "unknown method" {:connection-method connection-method})))))
