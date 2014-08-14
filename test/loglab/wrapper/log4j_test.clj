(ns loglab.wrapper.log4j-test
  (:import java.util.Random)
  (:require [loglab.wrapper.log4j :refer :all]))


(def logger-oops (get-formatter-logger "oops"))
(def random (new Random))
(defn random-integer
  []
  (.nextInt random 60000))

(log logger-oops :error "FuckingJava")

(defn make-log
  []
  (let [sum (+ (random-integer) (random-integer))]
    (cond
      (> sum 120000) {:level :error :msg (format "%6d is too big." sum)}
      (> sum 90000) {:level :warn :msg (format "%6d is going to be too big." sum)}
      (> sum 60000) {:level :debug :msg (format "%6d is just okay." sum)}
      (> sum 30000) {:level :info :msg (format "%6d is going to be too small." sum)}
      :else {:level :fatal :msg (format "%6d is too small." sum)})))

(dotimes [i 1000]
  (let [log (make-log)
        wrapper (wrap-logger logger-oops)]
    (wrapper (:level log) (:msg log))))
