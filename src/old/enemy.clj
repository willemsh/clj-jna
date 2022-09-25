(ns ctest.enemy
  (:require
    [ctest.ai :as ai]
    [ctest.fact :as f]
    [ctest.living :as l]
    [ctest.physical :as ph])
  (:gen-class))

(defonce enemies (atom []))

(defn init-enemy! [enemy]
  (swap! enemy ai/init-ai)
  (swap! l/init-normal-living enemy 20 (java.util.Date.)))

(defn get-enemy [name]
  (let [enemy {}]
    (-> enemy
        (ph/init-physical)
        (l/init-normal-living 20 (java.util.Date.))
        (ai/init-ai)
        (ai/add-to-memory [:name :is name]))))

(defn init-enemies! []
  (swap! enemies conj (get-enemy "Bad Guy"))
  (swap! enemies conj (get-enemy "Very Bad Guy")))


