(ns more-cd-cljs.macros)
(import java.io.File)

(def dirs ["Audio CD 1" "Audio CD 2" "Workbook"])

(defmacro m []
  (into {}
        (for [dir dirs]
          [dir (mapv #(.getPath %)
                     (filter #(.endsWith (.getName %) ".m4a")
                             (file-seq (File. dir))))])))

(defmacro defm [sym v m & body]
  `(def ~sym (with-meta (fn ~v ~@body) ~m)))
