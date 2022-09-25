(ns ctest.ai)

(defn init-ai [ai]
   (assoc ai :ai-state {:stimuli clojure.lang.PersistentQueue/EMPTY
                        :memory {:short-term [] :long-term []}
                        :sensor-organs {}
                        :feelings {}
                        :consciens-thought {:max 3 :thoughts []}
                        :subconsciens {:max 50 :thoughts []}
                        :actions []}))

(defn add-to-memory [ai fact]
  (let [ais (:ai-state ai)
        m (:memory ais)
        st (:short-term m)
        new-st (conj st fact)
        new-m (assoc m :short-term new-st)
        new-ais (assoc ais :memory new-m)]
     (assoc ai :ai-state new-ais)))

(defn get-answer [ai question]
  (let [ais (:ai-state ai)
        memory (:memory ais)
        st (memory :short-term)
        lt (memory :long-term)]
    (loop [i 0]
      (do
        (if (> (+ i 1) (count st))
          nil
          (if (question (st i))
            (st i)
            (recur (inc i))))))))

