(ns yushing-twitter.core
  (:require [yushing-twitter.yushing :as yushing]
            [overtone.at-at :as overtone]
            [twitter.api.restful :as twitter]
            [twitter.oauth :as twitter-oauth]
            [environ.core :refer [env]]))

(def my-creds
  (twitter-oauth/make-oauth-creds (env :app-consumer-key)
                                  (env :app-consumer-secret)
                                  (env :user-access-token)
                                  (env :user-access-secret)))

(def my-pool (overtone/mk-pool))

(defn gen-tweet []
  (->> yushing/rand-poem
       repeatedly
       (filter #(-> % count (<= 140)))
       first))

(defn status-update []
  (let [tweet (gen-tweet)]
    (println "generated tweet is :" tweet)
    (println "char count is:" (count tweet))
    (when (not-empty tweet)
      (try (twitter/statuses-update :oauth-creds my-creds
                                    :params {:status tweet})
           (catch Exception e (println "Oh no! " (.getMessage e)))))))

(defn -main [& args]
  ;; every hour
  (println "Started up")
  (overtone/every (* 1000 60 60 1) #(println (status-update)) my-pool))
