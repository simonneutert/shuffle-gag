(ns get-episode-spotify
  (:require [babashka.http-client :as http]
            [cheshire.core :as json]))

(def geschichten-aus-der-geschichte-show-id
  "0cPsvdqTreF6sKg6VwSrMl")

(def base-url
  (str "https://api.spotify.com/v1/shows/"
       geschichten-aus-der-geschichte-show-id
       "/episodes"))

(def auth-url
  "https://accounts.spotify.com/api/token")

(def auth-form-params
  {:form-params {"grant_type" "client_credentials"
                 "client_id" (System/getenv "SPOTIFY_CLIENT_ID")
                 "client_secret" (System/getenv "SPOTIFY_CLIENT_SECRET")}})

(defn get-token-auth
  []
  (let [response (http/post auth-url auth-form-params)]
    (json/parse-string (:body response) true)))

(defn get-episodes
  [token]
  (let [headers {"Authorization" (str "Bearer " token)
                 "Accept" "application/json"}
        query-params {"market" "DE"
                      "limit" "10"
                      "offset" "0"}
        response (http/get base-url {:headers headers :query-params query-params})]
    (json/parse-string (:body response) true)))

(defn get-latest-episode
  "Get the latest episode from the Spotify API.
   Auths with client credentials flow, then extracts the access token from the response.
   Then gets the episodes from the API and returns the first (newest) one."
  []
  (let [episodes (->> (get-token-auth)
                      :access_token
                      (get-episodes)
                      :items)]
    (clojure.pprint/pprint (take 3 episodes))
    (first episodes)))

(defn generate-unique-tag [episode]
  (let [title (:title episode)
        title-parts (clojure.string/split title #" ")
        title-first-letters (mapv #(get % 0) title-parts)]
    (clojure.string/join "" title-first-letters)))

(defn handle-special-episode [episode tagname]
  (if (= (:tag episode) tagname)
    (assoc episode :tag (str tagname "-" (generate-unique-tag episode)))
    episode))

(defn format-episode
  "Formats the episode to the desired edn/JSON format."
  [episode]
  (let [name (:name episode)
        url (:spotify (:external_urls episode))
        tag (clojure.string/lower-case (first (clojure.string/split name #":")))
        title (last (clojure.string/split name #": "))]
    {:title title
     :tag tag
     :url_spotify url}))

(let [json-content (-> (get-latest-episode)
                       (format-episode)
                       (handle-special-episode "extra")
                       (json/generate-string {:pretty true}))]
  (spit "newest-episode-spotify.json" json-content))
