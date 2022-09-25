(ns ctest.fact
  (:gen-class))

(defn is-name-fact? [fact]
  (and 
    (= (fact 0) :name) 
    (= (fact 1) :is)))
