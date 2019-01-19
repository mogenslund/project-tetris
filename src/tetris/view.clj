(ns tetris.view
  (:require [clojure.string :as str])
  (:import [javax.swing JPanel JFrame]
           [java.awt Dimension Toolkit Color]
           [java.net URL]))

(def rows 24)
(def columns 24)

(defn sleep
  [ms]
  (Thread/sleep ms))

(def state (atom {:squares (vec (take rows (repeat (vec (take columns (repeat "white"))))))}))

(defn mypaint
  [g]
  (doseq [r (range rows) k (range columns)]
    (.setColor g (Color/white))
    (let [c (get-in @state [:squares r k])]
      (cond (= c "black") (.setColor g (Color/black))
            (= c "yellow") (.setColor g (Color/yellow))
            (= c "blue") (.setColor g (Color/blue))
            (= c "red") (.setColor g (Color/red))
            (= c "gray") (.setColor g (Color/gray))
            (= c "green") (.setColor g (Color/green))
            (= c "white") (.setColor g (Color/white))
            (= c "sort") (.setColor g (Color/black))
            (= c "gul") (.setColor g (Color/yellow))
            (= c "blå") (.setColor g (Color/blue))
            (= c "rød") (.setColor g (Color/red))
            (= c "grå") (.setColor g (Color/gray))
            (= c "grøn") (.setColor g (Color/green))
            (= c "hvid") (.setColor g (Color/white)))
    (.fillRect g (+ 10 (* k 20)) (+ 10 (* r 20)) 20 20)
    (.setColor g (Color/gray))
    (.drawRect g (+ 10 (* k 20)) (+ 10 (* r 20)) 20 20))))


(def fr (JFrame.))

(defn get-frame
  []
  fr)

(def panel (proxy [JPanel] [] (paintComponent [g] (mypaint g))))

(defn square
  [r k color]
  (swap! state update :squares assoc-in [r k] color)
  (.repaint fr))


(defn reset
  []
  (doseq [r (range rows) k (range columns)]
    (square r k "white")))

(defn show
  []
  (.show fr)
  ;(.setPreferredSize fr (Dimension. (* 20 columns) (* 20 rows)))
  (.setPreferredSize panel (Dimension. (* 20 (+ columns 1)) (* 20 (+ rows 1))))
  (.add fr panel)
  (.pack fr))

(defn hide
  []
  (.dispose fr))

(def firkant square)
(def nulstil reset)
(def vis show)
(def skjul hide)

