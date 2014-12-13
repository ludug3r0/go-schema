(ns go.schema
  (:require [schema.core :as s]))


(defn valid?
  [schema value]
  (when-not (s/check schema value)
    value))

(def color
  (s/enum :black :white))

(def coordinate
  (let [positions (range 1 20)]
    (apply s/enum positions)))

(def vertex
  (s/pair coordinate 'column
          coordinate 'line))

(def placement
  (s/pair color 'color
          vertex 'vertex))

(def pass
  (s/eq :pass))

(def passing
  (s/pair color 'color
          pass 'pass))

(def resign
  (s/eq :resign))

(def resignation
  (s/pair color 'color
          resign 'resign))

(def move
  (s/either placement passing resignation))

(defn- optional-resignation-is-the-last-move?
  [moves]
  (let [resignations (filter (partial valid? resignation) moves)]
    (case (count resignations)
      0 true
      1 (valid? resignation (last moves))
      false)))

(def game
  (s/both
    [move]
    (s/pred optional-resignation-is-the-last-move? 'optional-resignation-is-the-last-move?)))

(def configuration
  #{placement})



