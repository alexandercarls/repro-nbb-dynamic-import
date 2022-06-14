(ns script
  (:require [nbb.core :refer [await]]))

;; Possible regression of #46

;; Doesn't work. nbb looks at the global node_modules folder first instead
;; of the relative one
;; when running with `nbb script.cljs`.

;; If you run `npm run test` (invokes locally installed nbb) it works.

;; If you remove locally installed nbb `npm uninstall nbb`,
;; the REPL doesn't work when evaluating the form (tested via Calva)
;; as it also doesn't resolve from the local node_modules folder

(def handle-term-size (await (js/import "term-size"))) ; ES-Module

;; This CommonJS Module can be loaded via 'require' just fine with local nbb and global nbb
(def handle-prismjs (js/require "prismjs/components/")) ; CommonJS Module

(prn (type handle-prismjs)) ;; #object[Function]
(prn (handle-term-size.default))
