(ns ctest.server
  (:require [org.httpkit.server :as s]
            [clj-time.format :as f]
            [compojure.core :refer :all]            
            [compojure.route :as r]
            [clojure.pprint :as pp]
            [clojure.data.json :as json]
            [ctest.html :as h]
            [ctest.player :as p])  
  (:gen-class))

    

(defn as-date-string [date]
  (f/unparse (f/formatter "YYYY-MM-dd") date))

(defn date-aware-value-writer [key value] 
  (if (= key :date-birth) (as-date-string value) value))

(defn show []  
  (h/build-html 
    "Player" 
    (str "<h1>Player</h1>" 
      "<pre>"
      ;(with-out-str (pp/pprint @p/player))
      "<script>let r = () => {window.location.reload(true); }; setTimeout(r, 5000)</script>"
      (java.util.Date.))))


(defn show-player [] 
  (json/write-str @p/player :value-fn date-aware-value-writer))            

(defn fps-handler [req] ;(3)  
  {:status 200   
   :headers {"Content-Type" "text/html"}   
   :body (show)})

(defn send-player [req] ;(3)  
  {:status 200   
   :headers {"Content-Type" "application/json"}   
   :body (show-player)})


(defn async-handler [ring-request]
  ;; unified API for WebSocket and HTTP long polling/streaming
  (s/with-channel ring-request channel    ; get the channel
    (if (s/websocket? channel)            ; if you want to distinguish them
      (s/on-receive channel (fn [data]     ; two way communication
                              (s/send! channel data)))
      (s/send! channel {:status 200
                        :headers {"Content-Type" "text/plain"}
                        :body    "Long polling?"}))))

;(run-server async-handler {:port 8089}) ; Ring server

(defroutes app-routes ;(3)  
  (GET "/" [] fps-handler)  
  (GET "/player" [] send-player) 
  ;(POST "/postoffice" [] mail-handler)  
  ;(ANY "/anything-goes" [] general-handler)  
  (r/not-found "You Must Be New Here")) ;(4)

(defn start ;(4)  
  "This is our app's entry point"  
  [& args]  
  (let [port (Integer/parseInt (or (System/getenv "PORT") "8089"))] ;(5)   
    (s/run-server #'app-routes {:port port})      (println (str "Running webserver at http:/127.0.0.1:" port "/"))))
