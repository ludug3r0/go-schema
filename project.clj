(defproject org.clojars.ludug3r0/go-schema "0.0.1-SNAPSHOT"
            :description "A set of schemas and utility function to operate on go data structures"
            :url "https://github.com/ludug3r0/go-schema"
            :license {:name "Eclipse Public License"
                      :url  "http://www.eclipse.org/legal/epl-v10.html"}
            :dependencies [[org.clojure/clojure "1.6.0"]
                           [prismatic/schema "0.3.3"]]
            :scm {:name "git"
                  :url  "https://github.com/ludug3r0/go-schema"}
            :profiles {:dev        {:dependencies [[expectations "2.0.9"]]
                                    :plugins [[lein-expectations "0.0.7"]]}})