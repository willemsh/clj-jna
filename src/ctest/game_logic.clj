(ns ctest.game-logic
  (:require
   [ctest.ai :as ai]
   [ctest.enemy :as e]
   [ctest.fact :as f]
   [ctest.living :as l]
   [ctest.player :as p]
   [ctest.physical :as ph])
  (:gen-class))

(defn init-game []
  (p/init-player!)
  (println (str "player " @p/player))
  (println (ai/get-answer @p/player f/is-name-fact?))
  ;(swap! player ph/move {:x 20 :y 20})
  ;(println (str "player " (get-position @p/player)))

  (e/init-enemies!)
  ;(println (str "enemies " @e/enemies))

  (map-indexed
   (fn [idx enemy] (println (ai/get-answer enemy f/is-name-fact?)))
   @e/enemies))

(defonce game (atom {:state :running}))

(defn update-logic [t]
  (loop [i 0]

    (Thread/sleep 100)
    ;(swap! player ph/move {:x (+ 20 i) :y 20})
    (println (str t " " i))
    (flush)
    (if (= 10000 i)
      i
      (recur (inc i)))))
