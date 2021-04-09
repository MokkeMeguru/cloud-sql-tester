(ns clojure-jdbc-tester.core
  (:gen-class)
  (:require [environ.core :refer [env]]
            [clojure.java.jdbc :as jdbc]))

(defn build-connection-options-postgres []
  {:dbtype "postgresql"
   :dbname (env :database-name)
   :host (env :database-host)
   :port (env :database-port)
   :user (env :database-user)
   :password (env :database-user-password)})

(defn build-connection-options [database-type]
  (cond
    (= database-type "postgresql") (build-connection-options-postgres)
    :else (throw (ex-info "Unknown database-type" {:database-type "database-type"}))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [database-type (env :database-type)
        connection-options (build-connection-options database-type)]
    (println "check your settings ...")
    (try
      (println (jdbc/get-connection connection-options))
      (println "Your settings is correct. have a fun to use Google Cloud SQL!")
      (catch Exception e
        (println "Your settings is not correct..." (.getMessage e))))))
