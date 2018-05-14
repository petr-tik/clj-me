(ns clj-me.core
  (:gen-class))


(def actors ["GSCO" "BAML" "JPMC"])

(def sides {:buy 0 :sell 1})

(def test-orders [{:actor "JPMC", :side 1, :amount 34, :price 7} {:actor "BAML", :side 0, :amount 96, :price 40} {:actor "BAML", :side 0, :amount 10, :price 1} {:actor "BAML", :side 1, :amount 37, :price 11} {:actor "GSCO", :side 0, :amount 2, :price 40}])

(defn generate-order []
  {:actor (rand-nth actors)
   :side (rand-nth (vals sides))
   :amount (inc (rand-int 100)) :price (inc (rand-int 50))})

(defn bucket-orders
  [orders]
  (let [bucketed-orders {}]
    (for [order orders
          :let [price (order :price)
                price-key (keyword (str price)) ]]
      (if (contains? bucketed-orders price-key)
        (update bucketed-orders price-key conj order)
        (assoc bucketed-orders price-key [order])))))

(defn process-orders
  [orders]


  )


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
