(ns advent.day1-captcha)

; https://adventofcode.com/2017/day/1

(defn read-captcha
  []
  (->> "day1-captcha"
       clojure.java.io/resource
       slurp
       char-array
       (map #(Character/getNumericValue %))))

(defn solve-captcha
  [captcha]
  (->> (conj captcha (first captcha))
       (partition 2 1)
       (filter #(apply = %))
       (map first)
       (apply +)))

; Puzzle 1
; (-> (read-captcha) solve-captcha)
; => 1182
