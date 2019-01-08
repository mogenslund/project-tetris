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
  (editor/insert "(vis)\n(firkant 0 0 \"sort\")\n(firkant 1 1 \"sort\")\n(skjul)")
  (editor/add-snippet "............................"))

(ns user
  (:require [tetris.view :refer [vis skjul firkant get-frame]]))
