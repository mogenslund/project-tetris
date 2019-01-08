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
  (editor/add-snippet "............................"))

