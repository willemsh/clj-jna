(ns test-setenv
  (:require [coffi.mem :as mem :refer [defalias]]
            [coffi.ffi :as ffi]
            [clojure.java.shell :as clj-shell]
            [clojure.string :as clj-str]))


; https://twitter.com/sundararajan_a/status/1470459967190679554?s=20
; There is "System.getenv" #Java API.

; https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/System.html#getenv(java.lang.String)

; But there's no "setenv" API! Let's use #PanamaProject

; https://openjdk.java.net/jeps/419

; in #jdk18 to call "setenv" C API from #Java

; https://man7.org/linux/man-pages/man3/setenv.3.html
; https://twitter.com/sundararajan_a/status/1470459967190679554/photo/1


(ffi/defcfn getenv
  getenv [::mem/c-string] ::mem/c-string)

(ffi/defcfn setenv
  setenv [::mem/c-string ::mem/c-string ::mem/long] ::mem/long)

(println (System/getenv "FOO"))
(println (getenv "FOO"))

(setenv "FOO" "BAR" 0)

(println (System/getenv "FOO"))
(println (getenv "FOO"))

(clj-shell/sh "ls" "-aul")

(println (clj-str/split  (:out (clj-shell/sh "env")) #"\n"))
(println (clj-str/split  (:out (clj-shell/sh "./getenv-test")) #"\n"))