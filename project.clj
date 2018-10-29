(defproject clorrent "0.1.0-SNAPSHOT"
  :description "A simple Clojure torrent client."
  :url "git@github.com:aaron-daly/clorrent.git"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [bencode "0.2.5"]
                 [udp-wrapper "0.1.1"]
                 [crypto-random "1.2.0"]
                 [clojurewerkz/buffy "1.1.0"]]
  :main ^:skip-aot clorrent.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
