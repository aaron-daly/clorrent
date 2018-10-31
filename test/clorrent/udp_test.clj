(ns clorrent.udp-test
  (:require [clojure.test :refer :all]
            [clorrent.udp :refer :all]))

(deftest extract-host-from-address-test
  (is (= "127.0.0.1"
         (extract-host-from-address "udp://127.0.0.1:1337")))
  (is (= "aaron.internal.clorrent"
         (extract-host-from-address "udp://aaron.internal.clorrent:1337")))
  (is (= nil
         (extract-host-from-address "http://127.0.0.1:1337"))))

(deftest extract-port-from-address-test
  (is (= 1337
         (extract-port-from-address "udp://127.0.0.1:1337")))
  (is (= 1337
         (extract-port-from-address "udp://aaron.internal.clorrent:1337")))
  (is (= nil
         (extract-port-from-address "http://127.0.0.1:1337"))))