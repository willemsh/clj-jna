(ns ctest.enemy
  (:require
   [brute.entity :as e]
   [ctest.component :as c]))


(defn create-enemy
  "Creates a enemy"
  [system]
  (let [enemy (e/create-entity) ;; Returns a UUID for the Entity
        ]
    (-> system
        (e/add-entity enemy) ;; Adds the entity to the ES data structure and returns it
        (e/add-component enemy (c/->Enemy)) ;; Adds the Ball instance to the ES data structure and returns it
        (e/add-component enemy (Living. java.util.Date. 200 200))
        (e/add-component enemy (c/->Physical (Position. 0 0))) ;; Adds the Rectangle instance to the ES data structure and returns it
        ;(e/add-component enemy (c/->Velocity (vector-2 0 300 :set-angle angle)))
        )))