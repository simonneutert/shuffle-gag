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

;; with Spotify support, we would check if the tags match and if the episode is unique before writing to data.json. If the tags don't match, we throw an error. If the episode is not unique, we also throw an error. Only if both checks pass, we write to data.json.
;; (if (valid-episode?)
;;   (let [all-episodes (cons (merge newest-apple-podcasts-episode-json newest-spotify-episode-json) all-json)]
;;     (spit "data.json" (json/generate-string all-episodes {:pretty true})))
;;   (throw (ex-info "Tags don't match" {:tag-apple-podcasts (:tag newest-apple-podcasts-episode-json)
;;                                       :tag-spotify (:tag newest-spotify-episode-json)})))

;; without Spotify support, we skip the tag-match check but still enforce uniqueness before writing to data.json.
(if (episode-unique? newest-apple-podcasts-episode-json)
  (let [all-episodes (cons newest-apple-podcasts-episode-json all-json)]
    (spit "data.json" (json/generate-string all-episodes {:pretty true}))))