(defproject org.clojars.ludug3r0/go-schema "0.0.3-SNAPSHOT"
            :description "A set of schemas and utility function to operate on cljx.go data structures"
            :url "https://github.com/ludug3r0/go-schema"
            :license {:name "Eclipse Public License"
                      :url  "http://www.eclipse.org/legal/epl-v10.html"}
            :dependencies [[org.clojure/clojure "1.6.0"]
                           [prismatic/schema "0.3.3"]]
            :scm {:name "git"
                  :url  "https://github.com/ludug3r0/go-schema"}
            :profiles {:dev {:dependencies [[expectations "2.0.9"]]
                             :plugins      [[com.keminglabs/cljx "0.4.0"]
                                            [lein-expectations "0.0.7"]]}}
            :source-paths ["target/generated/src/clj"]
            :test-paths ["test/clj"]
            :cljx {:builds [{:source-paths ["src/cljx"]
                             :output-path  "target/generated/src/clj"
                             :rules        :clj}
                            {:source-paths ["src/cljx"]
                             :output-path  "target/generated/src/cljs"
                             :rules        :cljs}]}
            :prep-tasks   [["with-profile" "+dev" ; Workaround for :dev cljx
                            "cljx" "once"]])