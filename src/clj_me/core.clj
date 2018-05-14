(ns clj-me.core
  (:gen-class))


(def actors ["GSCO" "BAML" "JPMC"])

(def sides {:buy 0 :sell 1})

(def test-orders [{:actor "JPMC", :side 1, :amount 34, :price 7} {:actor "BAML", :side 0, :amount 96, :price 40} {:actor "BAML", :side 0, :amount 10, :price 1} {:actor "BAML", :side 1, :amount 37, :price 11} {:actor "GSCO", :side 0, :amount 2, :price 40}])

(defn generate-order []
  {:actor (rand-nth actors)
   :side (rand-nth (vals sides))
   :amount (inc (rand-int 100)) :price (inc (rand-int 50))})


(defn add-new-order
  [bucketed-orders-so-far order]
  (let [price-key (keyword (str (:price order)))]
  (if (contains? bucketed-orders-so-far price-key)
        (update bucketed-orders-so-far price-key conj order)
        (assoc bucketed-orders-so-far price-key [order]))))

(defn bucket-orders
  [orders]
  (reduce add-new-order {} orders))

(defn process-orders
  [orders]


  )


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
