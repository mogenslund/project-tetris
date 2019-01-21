(nulstil)

(future
(doseq [c (range 24)
        r (range 24)]
  (sleep 10)
  (firkant r c "grøn")))

(Thread/sleep 2000)
(doseq [c (range 24)
        r (range 24)]
  (sleep 10)
  (firkant r c "blå"))

(nulstil)
(loop [r 0 c 0 counter 0]
  (when (< counter 1000)
    (let [r1 (rand-int 24)
          c1 (rand-int 24)]
      (sleep 2)
      (firkant r c "hvid")
      (firkant r1 c1 "rød")
      (recur r1 c1 (+ counter 1)))))

(nulstil)
    