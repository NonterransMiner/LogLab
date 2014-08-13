(ns loglab.gen.log4j
  (:import (org.apache.logging.log4j LogManager Logger)
           (clojure.lang Keyword)))

;(defn config-with-xml
;  "Config log4j 2 with XML configurations."
;  [^String path]
;  (DOMConfigurator/configure path))

;(defn config-with-default
;  "Config log4j 2 with default configurations, as a wrapper of BasicConfigurator.configure"
;  []
;  (BasicConfigurator/configure))

(defn new-logger
  "A wrapper of Logger.getLogger"
  [^String name]
  (LogManager/getLogger name))

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
  (fn [^Keyword level msg]
    (log target level msg)))
