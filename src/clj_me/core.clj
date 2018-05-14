(ns clj-me.core

  (:gen-class))


(def actors ["GSCO" "BAML" "JPMC"])

(def sides {:buy 0 :sell 1})

(def generate-order 
[]
{:actor (rand-nth actors) 
:side (rand-nth sides) 
:amount (inc (rand-int 100)) :price ((inc (rand-int 50)))}
)

(defn bucket-orders
[orders]
()

)

(defn process-orders
[orders]


)


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
