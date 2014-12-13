(ns go.models
  (:require [schema.core :as s]
            [go.schema :as schema]))

;; validators
(defn is-pass?
  [move]
  (schema/valid? schema/passing move))

(defn is-placement?
  [move]
  (schema/valid? schema/placement move))

(defn is-resignation?
  [move]
  (schema/valid? schema/resignation move))


;; move models
(s/defn color :- schema/color
  [move :- schema/move]
  (first move))

(s/defn move :- (s/either schema/vertex schema/pass schema/resign)
  [move :- schema/move]
  (second move))

;; game model
(s/defn stones :- [schema/placement]
  [game :- schema/game]
  (filter is-placement? game))

