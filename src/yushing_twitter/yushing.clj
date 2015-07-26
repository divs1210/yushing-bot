(ns yushing-twitter.yushing
  (:use [clojure.string :only (split join)]))

(def big-text
  (slurp "resources/hpl.txt"))

(def sentences
  (re-seq ;; Original:
          #"[A-Za-z \']+[\.]"
          ;; Below: http://stackoverflow.com/a/5553924/1163490
          ;; #"[^.!?\\s][^.!?]*(?:[.!?](?!['\"]?\\s|$)[^.!?]*)*[.!?]?['\"]?(?=\\s|$)"
          big-text))

(def sentence-db
  (map (fn [sentence]
         (rest (split (-> sentence
                          .trim
                          .toLowerCase)
                      #"[\ .]+")))
       sentences))

(def short-sentence-db
  (filter #(and (>= (count %) 3)
                (<= (count %) 5))
          sentence-db))

(defn rand-poem []
  (->> (shuffle short-sentence-db)
       (take 4)
       (map #(join " " %))
       (join "\n")))
