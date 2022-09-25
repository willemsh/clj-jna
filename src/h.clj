(ns h (:gen-class)
    (:require
     [brute.entity :as b-e]
     [ctest.player :as p]
     [ctest.enemy :as e]
     [net.n01se.clojure-jna :as jna])
    (:import [com.sun.jna Native Structure NativeLibrary]))


(defn get-input-state []
  (let [struct (jna/make-cbuf 16)
        pointer (jna/pointer struct)]
    (jna/invoke Integer cljjnarustclib/get_input_state pointer)
    (let [state {:up (.get struct 0)
                 :down (.get struct 1)
                 :left (.get struct 2)
                 :right (.get struct 3)
                 :space (.get struct 4)
                 :m_left (.get struct 5)
                 :m_right (.get struct 6)
                 :m_x (.getInt struct 8)
                 :m_y (.getInt struct 12)}]
     ;(println "up" up)
     ;(println "down" down)
     ;(println "left" left)
     ;(println "right" right)
     ;(println "space" space)
     ;(println "m_x" m_x)
     ;(println "m_y" m_y)
     ;(println "m_left" m_left)
     ;(println "m_right" m_right)
      state)))

(defn update-world [i x y t step]
  (jna/invoke Integer cljjnarustclib/update_world i x y t step))


(defn add-object [x y t step]
  (jna/invoke Integer cljjnarustclib/add_object x y t step))

(defn s [] (.getSeconds (java.util.Date.)))

(defn move-test [time]
  (try
    (let [state (get-input-state)]
      (update-world 0 (:m_x state) (:m_y state) 1 0)
      (update-world 1 20 (.getSeconds time) 1 0)
      (update-world 2 (.getSeconds time) 20 1 0))

    (catch Exception e (println (str "e" (.getMessage e))))))


(defn move-loop []
  (while true
    (let [t (java.util.Date.)]
      (move-test t))
    (Thread/sleep 20)))

(defn sdljnav []

  (try
    (future
      (jna/invoke Integer cljjnarustclib/run))

    (add-object 60 40 1 0)
    (add-object 60 40 0 0)

    (future (move-loop))

    (catch Exception e (println "e"))))


(def test-brute []
  (->
   (b-e/create-system)
   (p/create-player)
   (e/create-enemy)))


(comment

  (sdljnav)
  ;
  )