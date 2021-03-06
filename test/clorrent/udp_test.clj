(ns clorrent.udp-test
  (:require [clojure.test :refer :all]
            [clorrent.udp :refer :all])
  (:import (java.net DatagramSocket)))

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

(deftest create-socket-test
  (let [socket (create-socket 1337)]
    (is (= DatagramSocket
           (type socket)))
    (close-socket socket))
  (is (thrown?
        IllegalArgumentException
        (create-socket ""))))

(deftest close-socket-test
  (is (thrown?
        NullPointerException
        IllegalArgumentException
        (close-socket nil)))
  (let [socket (create-socket 1337)]
    (is (false? (.isClosed socket)))
    (close-socket socket)
    (is (true? (.isClosed socket)))))

(deftest create-receiver-test
  (let [socket (create-socket 1448)
        receiver (create-receiver socket println)]
    (is (not (nil? receiver)))
    (close-socket socket)))

(deftest close-receiver-test
  (let [socket (create-socket 1448)
        receiver (create-receiver socket println)]
    (is (true? (close-receiver receiver)))
    (close-socket socket))
  (is (thrown?
        NullPointerException
        IllegalArgumentException
        (close-receiver nil))))