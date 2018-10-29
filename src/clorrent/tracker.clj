(ns clorrent.tracker
  (:require [clorrent.udp :as udp]
            [clojurewerkz.buffy.core :as buffy]
            [crypto.random :as crypto.random])
  (:import (clojurewerkz.buffy.core BuffyBuf)))

(defn create-connection-request
  []
  (let [array-of-bytes (byte-array 16)]
    (-> (buffy/spec :connection-id-part-1 (buffy/int32-type)
                    :connection-id-part-2 (buffy/int32-type)
                    :action (buffy/int32-type)
                    :transaction-id (buffy/bytes-type 4))
        (buffy/compose-buffer)
        (buffy/set-field :connection-id-part-1 0x417)
        (buffy/set-field :connection-id-part-2 0x27101980)
        (buffy/set-field :action 0)
        (buffy/set-field :transaction-id (crypto.random/bytes 4))
        (.buffer)
        (.readBytes array-of-bytes))
    array-of-bytes))

(defn get-peers
  [torrent-announce]
  (let [udp-socket (udp/create-socket 1234)]
    (udp/send-packet-and-receive
      (create-connection-request)
      udp-socket
      (udp/extract-host-from-address torrent-announce)
      (udp/extract-port-from-address torrent-announce))))