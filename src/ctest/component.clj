(ns ^{:doc "All the components of the game"}
 ctest.component (:require
                  [ctest.ai :as ai]
                  ;[ctest.physical :as ph])
  ))


(defrecord Position [x y])

(defrecord Sensible [^clojure.lang.PersistentQueue stimuli
                     sensor-organs])

(defrecord Living [date-birth points max])
(defrecord Physical [^Position position])
(defrecord Player [])
(defrecord Enemy [])

(defrecord Conscious [max thoughts])
(defrecord WithSubconsciens [max thoughts])
(defrecord WithMemories [^ai/Memory short-term ^ai/Memory long-term])
(defrecord WithThoughts [consciens subconsciens])
(defrecord WithFeelings [max feelings])
