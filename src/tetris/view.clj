(ns tetris.view
  (:require [clojure.string :as str])
  (:import [javax.swing JPanel JFrame]
           [java.awt Dimension Toolkit Color]
           [java.net URL]))

(def rows 18)
(def columns 10)

(def state (atom {:squares (vec (take columns (repeat (vec (take rows (repeat "rød"))))))}))

(defn mypaint
  [g]
  (doseq [x (range rows) y (range columns)]
    (.setColor g (Color/white))
    (when (= (get-in @state [:squares x y]) "sort") (.setColor g (Color/black))
    (.fillRect g (* x 20) (* y 20) 20 20)
    (.setColor g (Color/gray))
    (.drawRect g (* x 20) (* y 20) 20 20))))


(def fr (JFrame.))

(defn get-frame
  []
  fr)
(def panel (proxy [JPanel] [] (paintComponent [g] (mypaint g))))

(defn firkant
  [x y color]
  (swap! state update :squares assoc-in [x y] color)
  (.repaint fr))

(defn vis
  []
  (.show fr)
  (.setPreferredSize fr (Dimension. 300 500))
  (.setPreferredSize panel (Dimension. 300 200))
  (.add fr panel)
  (.pack fr))

(defn skjul
  []
  (.dispose fr))

