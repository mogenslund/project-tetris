(defproject salzadk "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [mogenslund/liquid "1.0.1"]]
  :source-paths ["src"]
  :main ^:skip-aot editor.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})