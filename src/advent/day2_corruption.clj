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

(defn- calculate-diff
  "Returns the difference between the highest and lowest values in a list"
  [l]
  (- (apply max l) (apply min l)))

(defn calculate-checksum
  [spreadsheet]
  (->> spreadsheet
       (map calculate-diff)
       (apply +)))

; Puzzle 1
; (-> (read-spreadsheet) calculate-checksum)
; => 46402
