(ns loglab.gen.log4j-test
  (:import java.util.Random)
  (:require [loglab.gen.log4j :refer :all]))


(def logger-oops (new-logger "oops"))
(def random (new Random))
(defn random-integer
  []
  (.nextInt random 60000))

(log logger-oops :error "FuckingJava")

(defn make-log
  []
  (let [sum (+ (random-integer) (random-integer))]
    (cond
      (> sum 120000) {:level :error :msg (format "%d is too big." sum)}
      (> sum 90000) {:level :warn :msg (format "%d is going to be too big." sum)}
      (> sum 60000) {:level :debug :msg (format "%d is just okay." sum)}
      (> sum 30000) {:level :info :msg (format "%d is going to be too small." sum)}
      :else {:level :fatal :msg (format "%d is too small." sum)})))

(dotimes [i 1000]
  (let [log (make-log)
        wrapper (wrap-logger logger-oops)]
    (wrapper (:level log) (:msg log))))
