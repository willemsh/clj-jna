(ns ctest.player
  (:require
    [ctest.ai :as ai]
    [ctest.fact :as f]
    [ctest.living :as l]
    [ctest.physical :as ph])
  (:gen-class))

(defonce player (atom {}))

;(defn say! [ai expresion]
;  (println expresion))

(defn ^:private init-player [a]
  (let [player {}]
    (-> player 
        (ph/init-physical)
        (l/init-normal-living 200 (java.util.Date.))
        (ai/init-ai)
        (ai/add-to-memory [:name :is "Johnny"]))))

(defn init-player! []
  (swap! player init-player))
