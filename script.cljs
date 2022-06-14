;; Possible regression of #46
;; nbb doesn't resolve local node_modules when using the dynamic `js/import`
(ns script
  (:require [nbb.core :refer [await]]))

;; running (global) nbb `nbb script.cljs`
;; >> throws Error: Cannot find package 'term-size' imported from /opt/homebrew/lib/node_modules/nbb/lib/nbb_core.js

;; running (local) nbb (see package.json) `npm run nbb script.cljs`
;; this invokes the locally installed nbb, the "script.cljs" arguments then gets passed to it.
;; >> e.g. #js {:columns 110, :rows 16}

(def handle-term-size (await (js/import "term-size"))) ; ES-Module
(prn (handle-term-size.default))
