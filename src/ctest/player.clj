(ns ctest.player
            (:require
             [brute.entity :as e]
             [ctest.component :as c]))

(java.util.Date.)

(defn create-player
  "Creates a player"
  [system]
  (let [player (e/create-entity) ;; Returns a UUID for the Entity
        ]
    (-> system
        (e/add-entity player) ;; Adds the entity to the ES data structure and returns it
        (e/add-component player (c/->Player)) ;; Adds the Ball instance to the ES data structure and returns it
        ;(e/add-component player (c/->Living (Living. java.util.Date. 200 200)))
        (e/add-component player (c/->Physical (Position. 0 0))) ;; Adds the Rectangle instance to the ES data structure and returns it
        ;(e/add-component enemy (c/->Velocity (vector-2 0 300 :set-angle angle)))
        )))
