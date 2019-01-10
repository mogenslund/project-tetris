(ns editor.core
  (:require [clojure.string :as str]
            [dk.salza.liq.core :as liq-core]
            [dk.salza.liq.editor :as editor]))

(defn -main
  [& args]
  ;; Initialize
  (liq-core/set-defaults)
  (apply liq-core/startup args)
  (liq-core/init-editor)
  (editor/add-rootfolder "./src")
  (editor/add-searchpath "./src")
  (editor/insert (str/join "\n" (list
    "(vis)"
    "(firkant 0 0 \"sort\")"
    "(firkant 0 2 \"sort\")"
    "(firkant 1 1 \"sort\")"
    "(doseq [r (range 1 15)] (sleep 500) (firkant (- r 1) 1 \"hvid\") (firkant r 1 \"sort\"))"
    "(skjul)"
    "(nulstil)")))
  (editor/add-snippet "............................"))

(ns user
  (:require [tetris.view :refer
              [vis
               skjul
               firkant
               sleep
               get-frame nulstil]]))
