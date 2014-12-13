(ns go.models
  (:require [schema.core :as s :include-macros true]
            [go.schema :as schema]))

;; validators
(s/defn is-pass?
  [move :- schema/move]
  (schema/valid? schema/passing move))

(s/defn is-placement?
  [move :- schema/move]
  (schema/valid? schema/placement move))

(s/defn is-resignation?
  [move :- schema/move]
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

