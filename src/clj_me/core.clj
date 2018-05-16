(ns clj-me.core
  (:gen-class))

(def actors ["GSCO" "BAML" "JPMC"])
(def sides [:buy :sell])
(def test-orders [{:actor "JPMC", :side :sell, :amount 34, :price 7} {:actor "BAML", :side :buy, :amount 96, :price 40} {:actor "BAML", :side :buy, :amount 10, :price 1} {:actor "BAML", :side :sell, :amount 37, :price 11} {:actor "GSCO", :side :sell, :amount 20, :price 40}])

(defn make-order [actor amount price]
  {:actor actor
   :side (if (pos-int? amount) :buy :sell)
   :amount amount
   :price price})

(defn generate-order []
  {:actor (rand-nth actors)
   :side (rand-nth sides)
   :amount (inc (rand-int 100))
   :price (inc (rand-int 50))})

(defn add-order-to-buckets
  [bucketed-orders-so-far order]
  (let [price-key (keyword (str (:price order)))]
  (if (contains? bucketed-orders-so-far price-key)
        (update bucketed-orders-so-far price-key conj order)
        (assoc bucketed-orders-so-far price-key [order]))))

(defn bucket-orders [orders]
  (reduce add-order-to-buckets {} orders))

(defn process-order [order]
  (let [amount (:amount order)
        pick-amount (fn [order] (if (= :buy (:side order)) amount (- amount)))]
    (assoc order :amount (pick-amount order))))

(defn process-orders [orders]
  (map process-order orders))

;; (defn match-transactions [transactions] (reduce ...)

;;; FIXME: this doesn't behave well when they're the same amount
;;; TODO: Pick the actor from the transaction with the larger amount
(defn match-transaction [trans1 trans2]
  (let [amount1 (:amount trans1)
        amount2 (:amount trans2)]
    (if (neg-int? (* amount1 amount2)) ; they must be opposite directions
      (make-order "FOOBAR" (+ amount1 amount2) (:price trans1))
      nil)))                      ; nil means not a match

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (print (bucket-orders (process-orders test-orders))))
