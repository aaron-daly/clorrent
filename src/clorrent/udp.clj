(ns clorrent.udp
  (:require [clojure.string :as str]
            [udp-wrapper.core :as udp-wrapper]))

(defn extract-host-from-address
  [udp-address]
  (when (str/starts-with? udp-address "udp://")
    (subs udp-address 6 (str/last-index-of udp-address ":"))))

(defn extract-port-from-address
  [udp-address]
  (when (str/starts-with? udp-address "udp://")
    (-> udp-address
        (subs (inc (str/last-index-of udp-address ":"))
              (count udp-address))
        (Integer.))))

(defn create-socket
  [port]
  (udp-wrapper/create-udp-server port))

(defn close-socket
  [socket]
  (udp-wrapper/close-udp-server socket))

(defn create-receiver
  [socket fn]
  (udp-wrapper/receive-loop socket (udp-wrapper/empty-packet 512) fn))

(defn close-receiver
  [receiver]
  (future-cancel receiver))

(defn create-packet
  [byts host port]
  (udp-wrapper/packet byts (udp-wrapper/make-address host) port))

(defn send-packet
  [byts socket host port]
  (let [packet (create-packet byts host port)]
    (udp-wrapper/send-message socket packet)
    packet))

(defn send-packet-and-receive
  [byts socket host port]
  (let [packet (send-packet byts socket host port)]
    (udp-wrapper/receive-message-data socket packet)))