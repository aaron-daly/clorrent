(ns clorrent.core
  (:gen-class)
  [:require [bencode.metainfo.reader :as breader]]
  (:import (java.io FileNotFoundException)))

(defn -main
  [& args]
  (try
    (let [torrent-file-path (first args)
          torrent-metainfo (breader/parse-metainfo-file torrent-file-path)]
      (-> torrent-metainfo
          (breader/torrent-magnet-link)
          (println)))
    (catch FileNotFoundException _
      (println "Woops, we couldn't find that file!"))
    (catch Exception e
      (println e)
      (println "Oh no, something has gone horribly wrong! :("))))
