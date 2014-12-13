(ns go.test.schema
  (:require [go.schema :as schema]
            [schema.core :as s :include-macros true]
            #+clj [clojure.test :as t :refer [is deftest with-test run-tests testing]]
            #+cljs [cemerick.cljs.test :as t :refer-macros [is deftest with-test run-tests testing test-var]]))

(defn enable-prismatic-schema-checks
  [work]
  (s/with-fn-validation
    (work)))

(t/use-fixtures :once enable-prismatic-schema-checks)

(deftest possible-colors
  (is (s/validate schema/color :black))
  (is (s/validate schema/color :white))
  (is (thrown? #+clj Exception #+cljs js/Error (s/validate schema/color :gray))))

(deftest coordinates-range-from-1-to-19
  (is (s/validate schema/coordinate 1))
  (is (s/validate schema/coordinate 4))
  (is (s/validate schema/coordinate 19))
  (is (thrown? #+clj Exception #+cljs js/Error (s/validate schema/coordinate -1)))
  (is (thrown? #+clj Exception #+cljs js/Error (s/validate schema/coordinate 0)))
  (is (thrown? #+clj Exception #+cljs js/Error (s/validate schema/coordinate 20))))

(deftest vertex-is-a-composition-of-two-coordinates
  (is (s/validate schema/vertex [16 16]))
  (is (thrown? #+clj Exception #+cljs js/Error (s/validate schema/vertex [0 16])))
  (is (thrown? #+clj Exception #+cljs js/Error (s/validate schema/vertex [4 20]))))


(deftest stone-has-a-color-and-vertex
  (is (s/validate schema/placement [:black [1 1]]))
  (is (s/validate schema/placement [:white [19 19]])))

(deftest stone-should-be-inside-19-by-19-board
  (is (thrown? #+clj Exception #+cljs js/Error (s/validate schema/placement [:white [3 20]])))
  (is (thrown? #+clj Exception #+cljs js/Error (s/validate schema/placement [:black [0 4]]))))

(deftest a-placement-is-a-move
  (is (s/validate schema/placement [:black [3 4]]))
  (is (s/validate schema/move [:black [3 4]])))

(deftest passing-is-a-move
  (is (s/validate schema/passing [:black :pass]))
  (is (s/validate schema/move [:black :pass])))

(deftest resignation-is-a-move
  (is (s/validate schema/resignation [:black :resign]))
  (is (s/validate schema/move [:black :resign])))

(deftest a-game-can-have-no-moves
  (is (s/validate schema/game [])))

(deftest a-game-is-a-sequence-of-moves
  (is (s/validate schema/game [[:black [3 4]]
                               [:white [16 16]]
                               [:black :pass]])))

(deftest a-game-can-have-a-resignation
  (is (s/validate schema/game [[:black [3 4]]
                               [:white [16 16]]
                               [:black :resign]])))

(deftest a-game-can-only-have-a-single-resignation
  (is (thrown? #+clj Exception #+cljs js/Error (s/validate schema/game [[:black [3 4]]
                                                                        [:white [16 16]]
                                                                        [:black :resign]
                                                                        [:white :resign]]))))

(deftest if-resignation-is-present-it-must-be-the-last-move
  (is (thrown? #+clj Exception #+cljs js/Error (s/validate schema/game [[:black [3 4]]
                                                                        [:white [16 16]]
                                                                        [:black :resign]
                                                                        [:white :pass]]))))

(deftest a-configuration-is-a-set-of-stones
  (is (s/validate schema/configuration #{[:black [3 4]]
                                         [:white [16 16]]})))

(deftest a-configuration-can-have-no-stones
  (is (s/validate schema/configuration #{})))

(deftest a-configuration-does-not-contains-pass-moves
  (is (thrown? #+clj Exception #+cljs js/Error (s/validate schema/configuration [[:black [0 4]]
                                                                                 [:white :pass]]))))
