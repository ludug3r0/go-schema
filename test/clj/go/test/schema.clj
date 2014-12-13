(ns go.test.schema
  (:require [go.schema :refer :all]
            [schema.core :as s]
            [expectations :refer [expect]]))

;; black is a valid color
(expect (s/validate color :black))

;; white is a valid color
(expect (s/validate color :white))

;; coordinates range from 1 to 19
(expect (s/validate coordinate 1))
(expect (s/validate coordinate 4))
(expect (s/validate coordinate 19))
(expect Exception (s/validate coordinate -1))
(expect Exception (s/validate coordinate 0))
(expect Exception (s/validate coordinate 20))

;; vertex is a composition of two coordinates
(expect (s/validate vertex [16 16]))
(expect Exception (s/validate vertex [0 16]))
(expect Exception (s/validate vertex [4 20]))

;; stone has a color and a vertex
(expect (s/validate placement [:black [1 1]]))
(expect (s/validate placement [:white [19 19]]))

;; stone should be inside 19 by 19 board
(expect Exception (s/validate placement [:white [3 20]]))
(expect Exception (s/validate placement [:black [0 4]]))

;; a placement is a move
(expect (s/validate placement [:black [3 4]]))
(expect (s/validate move [:black [3 4]]))

;; passing is a move
(expect (s/validate passing [:black :pass]))
(expect (s/validate move [:black :pass]))

;; resignation is a move
(expect (s/validate resignation [:black :resign]))
(expect (s/validate move [:black :resign]))

;; a game can have no moves
(expect (s/validate game []))

;; a game is a sequence of moves
(expect (s/validate game [[:black [3 4]]
                          [:white [16 16]]
                          [:black :pass]]))

;; a game can have a resignation
(expect (s/validate game [[:black [3 4]]
                          [:white [16 16]]
                          [:black :resign]]))

;; a game can only have a single resignation
(expect Exception (s/validate game [[:black [3 4]]
                                    [:white [16 16]]
                                    [:black :resign]
                                    [:white :resign]]))

;; if resignation is present it is the last move
(expect Exception (s/validate game [[:black [3 4]]
                                    [:white [16 16]]
                                    [:black :resign]
                                    [:white :pass]]))


;; a configuration is a set of stones
(expect (s/validate configuration #{[:black [3 4]]
                                    [:white [16 16]]}))

;; a configuration can have no stones
(expect (s/validate configuration #{}))

;; a game configuration does not contains pass moves
(expect Exception (s/validate configuration [[:black [0 4]]
                                             [:white :pass]]))
