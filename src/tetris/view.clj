(ns tetris.view
  (:require [clojure.string :as str])
  (:import [javax.swing JPanel JFrame]
           [java.awt Dimension Toolkit Color]
           [java.net URL]))

(def rows 18)
(def columns 10)

(defn sleep
  [ms]
  (Thread/sleep ms))

(def state (atom {:squares (vec (take rows (repeat (vec (take columns (repeat "hvid"))))))}))

(defn mypaint
  [g]
  (doseq [r (range rows) k (range columns)]
    (.setColor g (Color/white))
    (let [c (get-in @state [:squares r k])]
      (cond (= c "sort") (.setColor g (Color/black))
            (= c "gul") (.setColor g (Color/yellow))
            (= c "blå") (.setColor g (Color/blue))
            (= c "rød") (.setColor g (Color/red))
            (= c "grå") (.setColor g (Color/gray))
            (= c "grøn") (.setColor g (Color/green)))
    (.fillRect g (+ 100 (* k 20)) (+ 100 (* r 20)) 20 20)
    (.setColor g (Color/gray))
    (.drawRect g (+ 100 (* k 20)) (+ 100 (* r 20)) 20 20))))


(def fr (JFrame.))

(defn get-frame
  []
  fr)

(def panel (proxy [JPanel] [] (paintComponent [g] (mypaint g))))

(defn firkant
  [r k color]
  (swap! state update :squares assoc-in [r k] color)
  (.repaint fr))

(defn nulstil
  []
  (doseq [r (range rows) k (range columns)]
    (firkant r k "hvid")))

(defn vis
  []
  (.show fr)
  (.setPreferredSize fr (Dimension. 500 600))
  (.setPreferredSize panel (Dimension. 500 600))
  (.add fr panel)
  (.pack fr))

(defn skjul
  []
  (.dispose fr))

