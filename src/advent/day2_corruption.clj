(ns advent.day2-corruption
  (:require [clojure.string :as str]))

; https://adventofcode.com/2017/day/2

(defn read-spreadsheet
  []
  (-> "day2-corruption"
      clojure.java.io/resource
      slurp
      (str/split #"\n")
      (->>
        (map #(str/split % #"\s"))
        (map (fn [row]
               (map #(Integer/parseInt %) row))))))

(defn calculate-diff
  "Returns the difference between the highest and lowest values in a list"
  [nums]
  (- (apply max nums) (apply min nums)))

(defn calculate-division-result
  "Given a list of numbers, find the two numbers that evenly divide and return the result"
  [nums]
  (letfn [(divisible? [a b]
            (let [res (/ a b)]
              (when (and (not= 1 res)
                         (int? res))
                    res)))
          (find-divisible [num]
            (some (partial divisible? num) nums))]
    ; Too lazy to not make this O(n^2) brute force since n is so small
    (some find-divisible nums)))

(defn sum-results
  [spreadsheet row-fn]
  (->> spreadsheet
       (map row-fn)
       (reduce +)))

; Puzzle 1
; (-> (read-spreadsheet) (sum-results calculate-diff))
; => 46402

; Puzzle 2
; (-> (read-spreadsheet) (sum-results calculate-division-result))
; => 265
