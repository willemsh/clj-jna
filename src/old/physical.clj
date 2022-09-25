(ns ctest.physical
  (:gen-class))

(defrecord Physical [x y])

(defn init-physical [physical]
   (assoc physical :physical-state {:position {:x 0 :y 0}}))

(defn move [physical position]
   (assoc physical :physical-state (assoc (:physical-state physical) :position position)))

(defn get-position [physical]
  (-> physical
      (:physical-state)
      (:position)))
