(ns tetris.core
  (:require [tetris.view :as view]))


(defn -main
  [& args]
  (println "Starter Tetris")
  (view/vis)
  (Thread/sleep 10000)
  (view/skjul))