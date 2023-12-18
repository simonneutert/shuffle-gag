apt-get -qq update -y && \
  apt-get -qq install -y jq httpie && \
  https "https://itunes.apple.com/lookup?id=1044844618&country=DE&media=podcast&entity=podcastEpisode&limit=10" > /tmp/itunes.json && \
  cat /tmp/itunes.json | \
    jq ".results[1] | {tag: ((.trackName|split(\":\")[0])|ascii_downcase), title: (.trackName|split(\": \")[1]), url: ([\"https://podcasts.apple.com/de/podcast/id1044844618?i=\",((.trackViewUrl|split(\"i=\")[1])|split(\"&\")[0])]|join(\"\"))}" > newest-episode.json