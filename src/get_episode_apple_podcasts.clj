(ns get-episode-apple-podcasts
  (:require [babashka.http-client :as http]
            [cheshire.core :as json]
            [clojure.string :as clojure.string]
            [clojure.pprint :as pprint]))

(def target-file
  "newest-episode-apple-podcasts.json")

(def geschichten-aus-der-geschichte-show-id
  "1044844618")

(def base-url
  (str "https://itunes.apple.com"
       "/lookup"))

(def query-params
  {:id geschichten-aus-der-geschichte-show-id
   :limit "10"
   :country "DE"
   :media "podcast"
   :entity "podcastEpisode"})

(defn get-episodes
  "Get the latest episodes from the Apple Podcasts API."
  []
  (let [response (http/get base-url {:query-params query-params})]
    (json/parse-string (:body response) true)))

(defn get-latest-episode
  "Get the latest episode from the Apple Podcasts API.
   The first result is the show itself."
  []
  (let [episodes (->> (get-episodes)
                      :results
                      (drop 1))]
    (clojure.pprint/pprint (take 3 episodes))
    (first episodes)))

(defn tag-from-name
  "Builds the tag from the episode name, e.g. 'gag420' from 'GAG420: Die Geschichte von der Geschichte'."
  [name]
  (->>
   (clojure.string/split name #":")
   first
   clojure.string/lower-case))

(defn title-from-name
  "Builds the title from the episode name, e.g.
   'Die Geschichte von der Geschichte' from 'GAG420: Die Geschichte von der Geschichte'."
  [name]
  (last (clojure.string/split name #": ")))

(defn short-url-from-track-view-url
  "Extracts the short URL from the track view URL, e.g.
   'https://podcasts.apple.com/de/podcast/id1044844618?i=1000497426249' from
   'https://podcasts.apple.com/de/podcast/gag420-die-geschichte-von-der-geschichte/id1044844618?i=1000497426249'."
  [track-view-url]
  (let [short-url (last (clojure.string/split track-view-url #"i="))
        episode-id (first (clojure.string/split short-url #"&"))]
    (str "https://podcasts.apple.com/de/podcast/id1044844618?i=" episode-id)))

(defn format-episode
  "Formats the episode to the desired edn/JSON format."
  [episode]
  (let [name (:trackName episode)
        url (short-url-from-track-view-url (:trackViewUrl episode))
        tag (tag-from-name name)
        title (title-from-name name)]
    {:title title
     :tag tag
     :url_apple_podcasts url}))

(defn generate-unique-tag [episode]
  (let [title (:title episode)
        title-parts (clojure.string/split title #" ")
        title-first-letters (mapv #(get % 0) title-parts)]
    (clojure.string/join "" title-first-letters)))

(defn handle-special-episode [episode tagname]
  (if (= (:tag episode) tagname)
    (assoc episode :tag (str tagname "-" (generate-unique-tag episode)))
    episode))

(let [json-content (-> (get-latest-episode)
                       (format-episode)
                       (handle-special-episode "extra")
                       (json/generate-string {:pretty true}))]
  (spit target-file json-content))