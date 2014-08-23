(ns loglab.wrapper.log4j
  (:import (org.apache.logging.log4j LogManager Logger)
           (clojure.lang Keyword IPersistentMap)
           (org.apache.logging.log4j.core.config ConfigurationFactory)
           (org.apache.logging.log4j.core.config.xml XmlConfigurationFactory)))

(defn get-logger
  "A wrapper of Logger.getLogger"
  [^String name]
  (LogManager/getLogger name))

(defn get-formatter-logger
  "A wrapper of Logger.getFormatterLogger"
  [^String name]
  (LogManager/getFormatterLogger name))

(defn log
  [logger level msg]
  (case level
    :info (.info logger msg)
    :warn (.warn logger msg)
    :debug (.debug logger msg)
    :error (.error logger msg)
    :fatal (.fatal logger msg)))

(defn wrap-logger
  [^Logger target]
  (fn
    ([{:keys [level msg]}]
     (log target level msg))))
