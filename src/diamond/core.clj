(ns diamond.core)

(require '[clojure.string :as string])

(defn char-index [letter] (- (int (first (char-array letter))) 65))

(defn inner-part [letter]
  (let [index (char-index letter)]
    (cond (= 0 index) letter
          :else (str letter (string/join "" (repeat (- (* 2 index) 1) "-")) letter))))

(defn outer-part [current-letter diamond-letter]
  (let [current-index (char-index current-letter)
        final-index (char-index diamond-letter)]
    (string/join "" (repeat (- final-index current-index) "-"))))

(defn line-for [current-letter diamond-letter]
  (let [outer (outer-part current-letter diamond-letter)
        inner (inner-part current-letter)]
    (str outer inner outer)))

(defn letter-sequence [diamond-letter]
  (let [value-a 65
        value-letter (+ value-a (char-index diamond-letter))]
    (map #(str (char %)) (concat (range value-a value-letter) (range value-letter (- value-a 1) -1)))))

(defn diamond [letter] (map #(line-for % letter) (letter-sequence letter)))

(defn print-diamond [letter] (doall (map println (diamond letter))))