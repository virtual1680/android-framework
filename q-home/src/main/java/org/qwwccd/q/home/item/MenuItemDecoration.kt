package org.qwwccd.q.home.item

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.devio.hi.library.util.HiDisplayUtil
import org.devio.hi.ui.item.HiAdapter


/**
 *mac
 *@author 晓风残月 2020/10/16 10
 *@email itperson@foxmail.com
 */
class MenuItemDecoration():RecyclerView.ItemDecoration() {

    private var mLayoutManager: LinearLayoutManager? = null

    private var listener: SortShowListener? = null

    fun StickyItemDecorator(listener: SortShowListener?) {
        this.listener = listener
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        if (parent.adapter is HiAdapter) {
            val adapter: HiAdapter = parent.adapter as HiAdapter
            if (adapter.itemCount <= 0) return
            val position: Int = (parent.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

            mLayoutManager = parent.layoutManager as LinearLayoutManager

            if (position >= 3) {
                listener?.showSort(true)
            } else {
                listener?.showSort(false)
            }
        }
    }
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        if (parent.adapter is HiAdapter) {
            val adapter: HiAdapter = parent.adapter as HiAdapter
            val position: Int = parent.getChildLayoutPosition(view)
//            if (position==4) {
//                outRect.set(0, HiDisplayUtil.dp2px(70f), 0, 0)
//            }
        }

    }
    interface SortShowListener {
        fun showSort(show: Boolean)
    }

}