(ns go.test.models
  (:require [go.models :as m]
            [schema.core :as s :include-macros true]
            #+clj [clojure.test :as t :refer [is deftest with-test run-tests testing]]
            #+cljs [cemerick.cljs.test :as t :refer-macros [is deftest with-test run-tests testing test-var]]))

(defn enable-prismatic-schema-checks
  [work]
  (s/with-fn-validation
    (work)))

(t/use-fixtures :once enable-prismatic-schema-checks)

;; can check if a move is a pass
(deftest can-check-if-a-move-is-a-pass
  (is (m/is-pass? [:black :pass]))
  (is (not (m/is-pass? [:black [16 16]])))
  (is (not (m/is-pass? [:black :resign]))))


(deftest can-check-if-a-move-is-a-placement
  (is (not (m/is-placement? [:black :pass])))
  (is (m/is-placement? [:black [16 16]]))
  (is (not (m/is-placement? [:black :resign]))))

(deftest can-check-if-a-move-is-a-resignation
  (is (not (m/is-resignation? [:black :pass])))
  (is (not (m/is-resignation? [:black [16 16]])))
  (is (m/is-resignation? [:black :resign])))

(deftest can-get-color
  (is (= :black (m/color [:black [15 13]])))
  (is (= :white (m/color [:white [1 1]])))
  (is (= :black (m/color [:black :pass])))
  (is (= :white (m/color [:white :resign]))))

(deftest can-get-the-move
  (is (= [13 1] (m/move [:white [13 1]])))
  (is (= :pass (m/move [:black :pass])))
  (is (= :resign (m/move [:white :resign]))))

(deftest can-get-stones
  (is (= [[:black [3 4]] [:white [7 4]]]
         (m/stones [[:black [3 4]] [:white [7 4]] [:black :pass] [:white :resign]]))))
