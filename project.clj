(defproject org.clojars.ludug3r0/go-schema "0.0.4-SNAPSHOT"
            :description "A set of schemas and utility function to operate on cljx.go data structures"
            :url "https://github.com/ludug3r0/go-schema"
            :license {:name "Eclipse Public License"
                      :url  "http://www.eclipse.org/legal/epl-v10.html"}
            :dependencies [[prismatic/schema "0.3.3"]]
            :scm {:name "git"
                  :url  "https://github.com/ludug3r0/go-schema"}
            :profiles {:dev {:dependencies [[org.clojure/clojure "1.6.0"]
                                            [org.clojure/clojurescript "0.0-2411"]
                                            [com.keminglabs/cljx "0.4.0" :exclusions [org.clojure/clojure]]]
                             :plugins      [[com.keminglabs/cljx "0.4.0" :exclusions [org.clojure/clojure]]
                                            [lein-cljsbuild "1.0.3"]
                                            [com.cemerick/clojurescript.test "0.3.3"]
                                            [lein-expectations "0.0.7"]]}}
            :source-paths ["target/generated/src/clj" "target/generated/src/cljs"]
            :test-paths ["target/generated/test/clj"]
            :cljx {:builds [{:source-paths ["src/cljx"]
                             :output-path  "target/generated/src/clj"
                             :rules        :clj}
                            {:source-paths ["src/cljx"]
                             :output-path  "target/generated/src/cljs"
                             :rules        :cljs}
                            {:source-paths ["test/cljx"]
                             :output-path  "target/generated/test/clj"
                             :rules        :clj}
                            {:source-paths ["test/cljx"]
                             :output-path  "target/generated/test/cljs"
                             :rules        :cljs}]}
            :cljsbuild {:builds [{:source-paths ["target/generated/src/cljs" "target/generated/test/cljs"]
                                  :compiler {:output-to "target/generated/js/test/testable.js"
                                             :optimizations :whitespace
                                             :pretty-print true}}]
                        :test-commands {"unit-tests" ["phantomjs" :runner
                                                      "target/generated/js/test/testable.js"]}}
            :aliases {"deploy" ["do" "clean," "cljx" "once," "deploy" "clojars"]
                      "test" ["do" "clean," "cljx" "once," "test," "with-profile" "dev" "cljsbuild" "test"]})

