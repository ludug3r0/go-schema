(ns go.test.models
  (:require [go.models :refer :all]
            [expectations :refer [expect]]))

;; can check if a move is a pass
(expect (is-pass? [:black :pass]))
(expect not (is-pass? [:black [16 16]]))
(expect not (is-pass? [:black :resign]))

;; can check if a move is a placement
(expect not (is-placement? [:black :pass]))
(expect (is-placement? [:black [16 16]]))
(expect not (is-placement? [:black :resign]))

;; can check if a move is a resignation
(expect not (is-resignation? [:black :pass]))
(expect not (is-resignation? [:black [16 16]]))
(expect (is-resignation? [:black :resign]))

;; can get color
(expect :black (color [:black [15 13]]))
(expect :white (color [:white [1 1]]))
(expect :black (color [:black :pass]))
(expect :white (color [:white :resign]))

;; can get the move
(expect [13 1] (move [:white [13 1]]))
(expect :pass (move [:black :pass]))
(expect :resign (move [:white :resign]))

;;stones
(expect [[:black [3 4]] [:white [7 4]]]
        (stones [[:black [3 4]] [:white [7 4]] [:black :pass] [:white :resign]]))