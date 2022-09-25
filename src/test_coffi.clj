(ns test-coffi
  (:require [coffi.mem :as mem]
            [coffi.ffi :as ffi]))
(ffi/load-system-library "cljjnarustclib")


(ffi/defcfn hello
  "Given a string, measures its length in bytes."
  hello [::mem/c-string] ::mem/void)

(hello "hola")

(ffi/defcfn how_many_characters
  "Given a string, measures its length in bytes."
  how_many_characters [::mem/c-string] ::mem/long)

(how_many_characters "hola")

(ffi/defcfn run
  "Given a string, measures its length in bytes."
  run [] ::mem/long)


(ffi/defcfn update-world
  "Given a string, measures its length in bytes."
  update_world [::mem/long ::mem/long ::mem/long ::mem/long ::mem/long] ::mem/long)


(ffi/defcfn add-object
  "Given a string, measures its length in bytes."
  add_object [::mem/long ::mem/long ::mem/long ::mem/long] ::mem/long)

(ffi/defcfn add-new-object
  "Given a string, measures its length in bytes."
  add_new_object [::mem/long ::mem/long ::mem/c-string ::mem/long] ::mem/long)


(ffi/defcfn move-new-object
  "Given a string, measures its length in bytes."
  move_new_object [::mem/long ::mem/long ::mem/long] ::mem/long)


(mem/defalias ::input-state
  [::mem/struct
   [[:up ::mem/byte]
    [:down ::mem/byte]
    [:left ::mem/byte]
    [:right ::mem/byte]
    [:space ::mem/byte]
    [:m_left ::mem/byte]
    [:m_right ::mem/byte]
    ;https://doc.rust-lang.org/reference/type-layout.html
    [:spacing1 ::mem/byte] ; very important!  The alignment of the struct is the alignment of the most-aligned field in it.
    [:m_x ::mem/int]
    [:m_y ::mem/int]]])

(ffi/defcfn get-input-state
  "Given a string, measures its length in bytes."
  get_input_state [::mem/pointer] ::mem/long)

(ffi/defcfn get-input-state
  "get_input_state" [::mem/pointer] ::mem/void
  native-fn
  [input-state]
  (let [;old-input-state (mem/deserialize input-state [::mem/pointer :test-coffi/input-state])
        ;_ (println old-input-state)
        _ (native-fn input-state)
        new-input-state (mem/deserialize input-state [::mem/pointer :test-coffi/input-state])
        ;_ (println new-input-state)
        ]
    new-input-state))


(defn s [] (.getSeconds (java.util.Date.)))

(def ism (mem/alloc-instance :test-coffi/input-state))
(println "ism")
(println ism)

(def ism-p (mem/address-of ism))
(println "ism-p")
(println ism-p)

;(get-input-state ism-p)

(def ism-p-s (mem/serialize ism-p ::mem/pointer))
(println "ism-p-s (not neeeded)")
(println ism-p-s)

(println "ism-p deserialized")
(println (mem/deserialize ism-p [::mem/pointer :test-coffi/input-state]))


(defn move-test [time]
  (try
    (let [state (get-input-state ism-p)]
      ;(update-world 0 (:m_x state) (:m_y state) 1 0)
      (update-world 1 20 (.getSeconds time) 1 0)
      (update-world 2 (.getSeconds time) 20 1 0)
      (move-new-object 1 (:m_x state) (:m_y state)))
    (catch Exception e (println (str "e move-test" (.getMessage e))))))


(defn move-loop []
  (while true
    (let [t (java.util.Date.)]
      (move-test t))
    (Thread/sleep 20)))

(def textures {:pikachu "resources/pikachu.png"
               :test "resources/test.png"})

(defn sdljnav []
  (try
    (future (run))

    (add-object 60 40 1 0)
    (add-object 60 40 0 0)

    (add-new-object 160 140 (:pikachu textures) 2)
    (add-new-object 180 140 (:test textures) 3)
    (add-new-object 190 140 (:test textures) 4)
    (add-new-object 170 70 (:test textures) 5)

    (future (move-loop))

    (catch Exception e (println (str "e sdljnav" (.getMessage e))))))


(comment
  (sdljnav)

  (def ism (mem/alloc-instance :test-coffi/input-state))
  (println ism)

  (def ism-p (mem/address-of ism))
  (println ism-p)

  (def ism-clj (mem/deserialize ism :test-coffi/input-state))
  (println ism-clj)
  ;

  (mem/size-of ::mem/int)

  (mem/size-of ::mem/long)

  (mem/size-of :test-coffi/input-state)
  ;
  )