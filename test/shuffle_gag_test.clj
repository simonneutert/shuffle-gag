(ns shuffle-gag-test
  (:require [clojure.test :refer :all]
            [get-episode-apple-podcasts :refer :all]
            [babashka.http-client :as http]))

(deftest test-get-episodes
  (with-redefs [http/get (fn [_ _] {:body "{\"results\": [{\"trackName\": \"GAG420: Die Geschichte von der Geschichte\"}]}"})]
    (let [episodes (get-episodes)]
      (is (= (count (:results episodes)) 1))
      (is (= (:trackName (first (:results episodes))) "GAG420: Die Geschichte von der Geschichte")))))

(deftest test-get-latest-episode
  (with-redefs [http/get (fn [_ _] {:body "{\"results\": [{\"trackName\": \"Show\"}, {\"trackName\": \"GAG420: Die Geschichte von der Geschichte\"}]}"})]
    (let [latest-episode (get-latest-episode)]
      (is (= (:trackName latest-episode) "GAG420: Die Geschichte von der Geschichte")))))

(deftest test-tag-from-name
  (is (= (tag-from-name "GAG420: Die Geschichte von der Geschichte") "gag420")))

(deftest test-title-from-name
  (is (= (title-from-name "GAG420: Die Geschichte von der Geschichte") "Die Geschichte von der Geschichte")))

(deftest test-short-url-from-track-view-url
  (is (= (short-url-from-track-view-url "https://podcasts.apple.com/de/podcast/gag420-die-geschichte-von-der-geschichte/id1044844618?i=1000497426249")
         "https://podcasts.apple.com/de/podcast/id1044844618?i=1000497426249")))

(deftest test-format-episode
  (let [episode {:trackName "GAG420: Die Geschichte von der Geschichte"
                 :trackViewUrl "https://podcasts.apple.com/de/podcast/gag420-die-geschichte-von-der-geschichte/id1044844618?i=1000497426249"}
        formatted-episode (format-episode episode)]
    (is (= (:title formatted-episode) "Die Geschichte von der Geschichte"))
    (is (= (:tag formatted-episode) "gag420"))
    (is (= (:url_apple_podcasts formatted-episode) "https://podcasts.apple.com/de/podcast/id1044844618?i=1000497426249"))))

(deftest test-generate-unique-tag
  (let [episode {:title "Die Geschichte von der Geschichte"}]
    (is (= (generate-unique-tag episode) "DGvdG"))))

(deftest test-handle-special-episode
  (let [episode {:tag "extra" :title "Die Geschichte von der Geschichte"}
        handled-episode (handle-special-episode episode "extra")]
    (is (= (:tag handled-episode) "extra-DGvdG"))))

(run-tests)