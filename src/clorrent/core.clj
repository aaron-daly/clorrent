(ns clorrent.core
  (:gen-class)
  [:require [bencode.metainfo.reader :as breader]
            [clorrent.tracker :as tracker]]
  (:import (java.io FileNotFoundException)))

(defn -main
  [& args]
  (try
    (let [torrent-file-path (first args)
          torrent-metainfo (breader/parse-metainfo-file torrent-file-path)
          torrent-announce (breader/torrent-announce torrent-metainfo)
          _ (tracker/get-peers torrent-announce)])
    (catch FileNotFoundException _
      (println "Woops, we couldn't find that file!"))
    (catch Exception e
      (println "Oh no, something has gone horribly wrong! :(") e)))
