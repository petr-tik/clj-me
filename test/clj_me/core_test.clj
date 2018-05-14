(ns clj-me.core-test
  (:require [clojure.test :refer :all]
            [clj-me.core :refer :all]
            [clojure.spec.test.alpha :as stest]
            [clojure.spec.alpha :as s]
            ))


(s/def ::side boolean?)
(s/def ::amount int?)
(s/def ::price int?)

(s/def ::order (s/keys :req [::side ::amount ::price]))

(defn order-crunch
  [order]
  order)

(s/fdef order-crunch
        :args int?
        :ret int?
        :fn #(= (:ret %) (:args %)))


(stest/check `order-crunch)
