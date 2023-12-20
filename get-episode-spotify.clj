(require '[babashka.http-client :as http]
         '[cheshire.core :as json])

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
  (let [response (http/get base-url {:headers {"Authorization" (str "Bearer " token)
                                               "Accept" "application/json"}
                                     :query-params {"market" "DE"
                                                    "limit" "10"
                                                    "offset" "0"}})]
    (json/parse-string (:body response) true)))

(defn get-latest-episode
  []
  (->> (get-token-auth)
       :access_token
       (get-episodes)
       :items
       first))

(defn format-episode
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
                       (json/generate-string {:pretty true}))]
  (spit "newest-episode-spotify.json" json-content))
