(ns tetris.view
  (:require [clojure.string :as str])
  (:import [javax.swing JPanel JFrame]
           [java.awt Dimension Toolkit Color]
           [java.net URL]))

(def rows 24)
(def columns 24)
(def colormap (atom {
  "black" (Color/black)
  "yellow" (Color/yellow)
  "blue" (Color/blue)
  "red" (Color/red)
  "gray" (Color/gray)
  "green" (Color/green)
  "white" (Color/white)
  "sort" (Color/black)
  "gul" (Color/yellow)
  "blå" (Color/blue)
  "rød" (Color/red)
  "grå" (Color/gray)
  "grøn" (Color/green)
  "hvid" (Color/white)
  }))

(defn add-color
  [name r g b]
  (swap! colormap assoc name (Color. r g b)))

(defn sleep
  [ms]
  (Thread/sleep ms))

(def state (atom {:squares (vec (take rows (repeat (vec (take columns (repeat "white"))))))}))

(defn mypaint
  [g]
  (doseq [r (range rows) k (range columns)]
    (.setColor g (Color/white))
    (let [c (get-in @state [:squares r k])]
      (.setColor g (@colormap c))
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

(defn big-square
  [r k h b color]
  (doseq [r1 (range h) k1 (range b)]
    (square (+ r r1) (+ k k1) color)))

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
(def stor-firkant big-square)
(def nulstil reset)
(def vis show)
(def skjul hide)
(def ny-farve add-color)

