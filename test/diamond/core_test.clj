(ns diamond.core-test
  (:require [clojure.test :refer :all]
            [diamond.core :refer :all]))

(deftest char-indexing
  (testing "A"
    (is (= 0 (char-index "A"))))
  (testing "B"
    (is (= 1 (char-index "B"))))
  (testing "C"
    (is (= 2 (char-index "C"))))
  (testing "Z"
    (is (= 25 (char-index "Z")))))

; start with inner because it doesnt it's 'diamond-independent'
(deftest inner-part-building
  (testing "A"
    (is (= "A" (inner-part "A"))))
  (testing "B"
    (is (= "B-B" (inner-part "B"))))
  (testing "C"
    (is (= "C---C" (inner-part "C"))))
  (testing "D"
    (is (= "D-----D" (inner-part "D")))))

; now let's do the outer part calculation
(deftest outer-part-building
  (testing "A for A diamond"
    (is (= "" (outer-part "A" "A"))))
  (testing "A for B diamond"
    (is (= "-" (outer-part "A" "B"))))
  (testing "A for C diamond"
    (is (= "--" (outer-part "A" "C"))))
  (testing "B for F diamond"
    (is (= "----" (outer-part "B" "F")))))

; a line should be a simple combination of outers and inner part
(deftest line-building
  (testing "A for A diamond"
    (is (= "A" (line-for "A" "A"))))
  (testing "A for C diamond"
    (is (= "--A--" (line-for "A" "C"))))
  (testing "C for C diamond"
    (is (= "C---C" (line-for "C" "C")))))

; now, to put all lines we need to figure out the sequence of letters
(deftest diamond-letters-building
  (testing "A diamond"
    (is (= '("A") (letter-sequence "A"))))
  (testing "D diamond"
    (is (= '("A" "B" "C" "D" "C" "B" "A") (letter-sequence "D")))))

; finally, diamond building is just a map function over the letters
(deftest diamond-building
  (testing "Diamond for A"
    (is (= '("A") (diamond "A"))))
  (testing "Diamond for B"
    (is (= '("-A-", "B-B", "-A-") (diamond "B"))))
  (testing "Diamond for C"
    (is (= '("--A--", "-B-B-", "C---C", "-B-B-", "--A--") (diamond "C"))))
  (testing "Diamond for D"
    (is (= '("---A---", "--B-B--", "-C---C-", "D-----D","-C---C-", "--B-B--", "---A---") (diamond "D")))))