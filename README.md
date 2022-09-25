# clj-jna

This clojure app is part of a project to show how to communicate between Clojure and Rust.

It's completely unorganized.

I began developement before FFI (Project Panama) was available, thus the name, latest implementation uses the coffi library:

https://github.com/IGJoshua/coffi

To run it its necesary to build cljjnarustclib and update the path in deps.edn

clj -A:coffi-dir

WARNING: Using incubator modules: jdk.incubator.foreign
Clojure 1.10.1
(load-file "./src/test_coffi.clj")
