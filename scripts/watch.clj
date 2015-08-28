(require '[cljs.build.api :as b])

(b/watch "src"
  {:main 'more-cd-cljs.core
   :output-to "out/more_cd_cljs.js"
   :output-dir "out"})
