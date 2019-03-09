package com.shojishunsuke.pronounceflachcard

import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView

class OnlyOneScroller(val recyclerView: RecyclerView):LinearSmoothScroller(recyclerView.context) {

    override fun updateActionForInterimTarget(action: Action?) {
    }
}