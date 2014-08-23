(ns loglab.wrapper.slf4j
  (:import (org.slf4j Logger LoggerFactory)
           (clojure.lang Keyword)))

(defn get-logger
  [^String name]
  (LoggerFactory/getLogger name))

(defn log
  [logger level msg]
  (case level
    :info (.info logger msg)
    :warn (.warn logger msg)
    :debug (.debug logger msg)
    :error (.error logger msg)))

(defn wrap-logger
  [^Logger target]
  (fn [{:keys [level msg]}]
    (log target level msg)))
