(ns s (:require
       [ctest.game-logic :as gl]
       ;[ctest.server :as s])
    ))
;
;nivel de relajación ducharse
;nivel de sueño dormir
;nivel de hablre comer
;
(defn -main []
  (gl/init-game)
  ;(s/start)

  (println (str "Starting thread " 1))
  (.start
   (Thread. (partial gl/update-logic 1))))