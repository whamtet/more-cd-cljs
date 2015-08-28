(ns more-cd-cljs.core
  (:require [reagent.core :as reagent :refer [atom]])
  (:require-macros [more-cd-cljs.macros :as m])
  )

(enable-console-print!)

(def selected-track (atom nil))

(m/defm contents []
  {:component-did-update #(if-let [x (js/document.getElementById "audio")]
                            (.load x))
   }
  [:div
   #_(for [dir (keys (m/m))]
       ^{:key dir}
       [:div [:a {:href (str "#" dir)} dir]])
   (if @selected-track
     [:div
      [:div (re-find #"Track \d+" @selected-track)]
      [:audio {:controls true :id "audio"}
       [:source {:src @selected-track}]]])
   (for [[dir tracks] (m/m)]
     ^{:key dir}
     [:div
      [:h3 {:id dir} dir]
      (for [track tracks]
        ^{:key track}
        [:span
         [:a {:href "javascript:void(0)"
              :on-click #(reset! selected-track track)}
          (re-find #"Track \d+" track)
          ] " "])])])


(defn page3 []
  (reagent/render-component
   [:div {:id "content"}
    [contents]]
   (js/document.getElementById "content")))

(defn main []
  (page3))
