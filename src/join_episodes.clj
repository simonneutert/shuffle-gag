(ns join-episodes
  (:require [cheshire.core :as json]))

(def all
  (slurp "data.json"))

(def newest-apple-podcasts-episode
  (slurp "newest-episode-apple-podcasts.json"))

(def newest-spotify-episode
  (slurp "newest-episode-spotify.json"))

(def all-json
  (into [] (json/parse-string all true)))

(def newest-apple-podcasts-episode-json
  (json/parse-string newest-apple-podcasts-episode true))

(def newest-spotify-episode-json
  (json/parse-string newest-spotify-episode true))

(defn tags-match?
  "Checks if the tags match."
  []
  (or (= (:tag newest-apple-podcasts-episode-json)
         (:tag newest-spotify-episode-json))
      (throw (ex-info "Tags don't match" {:tag-apple-podcasts (:tag newest-apple-podcasts-episode-json)
                                          :tag-spotify (:tag newest-spotify-episode-json)}))))

(defn episode-unique?
  "Checks if the episode is unique."
  [new-episode]
  (or (not-any? #(= (:tag %)
                    (:tag new-episode))
                all-json)
      (throw (ex-info "Episode is not unique" {:tag (:tag new-episode)}))))

(defn valid-episode?
  "Checks if the episode is valid."
  []
  (let [new-episode (merge newest-apple-podcasts-episode-json newest-spotify-episode-json)]
    (and (tags-match?)
         (episode-unique? new-episode))))

(if (valid-episode?)
  (let [all-episodes (cons (merge newest-apple-podcasts-episode-json newest-spotify-episode-json) all-json)]
    (spit "data.json" (json/generate-string all-episodes {:pretty true})))
  (throw (ex-info "Tags don't match" {:tag-apple-podcasts (:tag newest-apple-podcasts-episode-json)
                                      :tag-spotify (:tag newest-spotify-episode-json)})))