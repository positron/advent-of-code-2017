(ns advent.day1-captcha)

; https://adventofcode.com/2017/day/1

(defn read-captcha
  []
  (->> "day1-captcha"
       clojure.java.io/resource
       slurp
       char-array
       (map #(Character/getNumericValue %))))

(defn- add-matching-numbers
  [number-pairs]
  (->> number-pairs
       (filter #(apply = %))
       (map first)
       (apply +)))

(defn solve-captcha-1
  [captcha]
  (->> (conj captcha (first captcha))
       (partition 2 1)
       (add-matching-numbers)))

(defn solve-captcha-2
  [captcha]
  (let [captcha (vec captcha)
        half (/ (count captcha) 2)]
    (->> captcha
         (partition half)
         (apply map vector) ; zip up the two lists
         add-matching-numbers
         (* 2)))) ; going through the second half of the list will have the same result as the first half

; Puzzle 1
; (-> (read-captcha) solve-captcha-1)
; => 1182

; Puzzle 2
; (-> (read-captcha) solve-captcha-2)
; => 1152
