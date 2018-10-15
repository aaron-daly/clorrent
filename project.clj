(defproject clorrent "0.1.0-SNAPSHOT"
  :description "A simple Clojure torrent client."
  :url "git@github.com:aaron-daly/clorrent.git"
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot clorrent.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
