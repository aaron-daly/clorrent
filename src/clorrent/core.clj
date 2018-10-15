(ns clorrent.core
  (:gen-class)
  (:import (java.io FileNotFoundException)))

(defn -main
  [& args]
  (try
    (-> args
        (first)
        (slurp)
        (println))
    (catch FileNotFoundException _
      (println "Woops, we couldn't find that file!"))
    (catch Exception _
      (println "Oh no, something has gone horribly wrong! :("))))
