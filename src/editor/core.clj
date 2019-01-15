(ns editor.core
  (:require [clojure.string :as str]
            [tetris.view :as view]
            [dk.salza.liq.apps.textapp :as textapp]
            [dk.salza.liq.core :as liq-core]
            [dk.salza.liq.editor :as editor]))

(defn f5-eval
  []
  (editor/highlight-sexp-at-point)
  (editor/eval-last-sexp)
  (Thread/sleep 300)
  (editor/highlight-sexp-at-point))

(defn f4-highlight
  []
  (editor/highlight-sexp-at-point)
  (editor/updated)
  (Thread/sleep 300)
  (editor/highlight-sexp-at-point))

(defn -main
  [& args]
  ;; Initialize
  (liq-core/set-defaults)
  (apply liq-core/startup args)
  (liq-core/init-editor)
  (editor/prompt-set "")
  (editor/clear)
  (editor/set-keymap @textapp/keymap-insert)
  (editor/add-rootfolder "./src")
  (editor/add-searchpath "./src")
  (editor/add-snippet (str/join "\n" (list
    "(vis)"
    "(firkant 0 0 \"sort\")"
    "(firkant 0 2 \"sort\")"
    "(firkant 1 1 \"sort\")"
    "(doseq [r (range 1 15)] (sleep 500) (firkant (- r 1) 1 \"hvid\") (firkant r 1 \"sort\"))"
    "(skjul)"
    "(nulstil)")))
  (editor/add-snippet "(vis)")
  (editor/add-snippet "(nulstil)")
  (editor/add-snippet "(skjul)")
  (editor/add-snippet "(firkant 0 0 \"blå\")")
  (editor/set-global-key "f5" f5-eval)
  (editor/set-global-key "f4" f4-highlight)
  (editor/set-global-key "f6" #(editor/eval-sexp (editor/get-content)))
  (view/vis))

(ns user
  (:require [tetris.view :refer
              [show
               hide
               square
               vis
               reset
               skjul
               firkant
               sleep
               get-frame nulstil]]))
