(ns ctest.living
  (:require [clj-time.core :as t])
  (:gen-class))

(defrecord Living [date-birth age points max])

; as for now only "normal" living things
(defn init-normal-living [living life-points date-birth]
   (assoc living :life-state {:date-birth (t/now)
                              :age :normal
                              :points life-points
                              :max life-points}))
