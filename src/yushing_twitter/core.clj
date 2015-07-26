(ns yushing-twitter.core
  (:require [yushing-twitter.yushing :as yushing]
            [twitter.api.restful :as twitter]
            [twitter.oauth :as twitter-oauth]
            [environ.core :refer [env]]))

(def my-creds
  (twitter-oauth/make-oauth-creds (env :app-consumer-key)
                                  (env :app-consumer-secret)
                                  (env :user-access-token)
                                  (env :user-access-secret)))

(defn gen-tweet []
  (str (->> yushing/rand-poem
            repeatedly
            (filter #(-> % count (<= 117)))
            first)
       "\nhttp://yushing.herokuapp.com/"))

(defn status-update []
  (let [tweet (gen-tweet)]
    (println "generated tweet is :" tweet)
    (println "char count is:" (count tweet))
    (when (not-empty tweet)
      (try (twitter/statuses-update :oauth-creds my-creds
                                    :params {:status tweet})
           (catch Exception e (println "Oh no! " (.getMessage e)))))))
