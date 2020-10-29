package org.qwwccd.q.home.item

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import org.devio.hi.library.log.HiLog
import org.devio.hi.library.util.HiDisplayUtil
import org.devio.hi.ui.item.HiDataItem
import org.devio.hi.ui.item.HiViewHolder
import org.devio.hi.ui.tab.common.IHiTabLayout
import org.devio.hi.ui.tab.top.HiTabTopInfo
import org.devio.hi.ui.tab.top.HiTabTopLayout
import org.qwwccd.q.home.R


/**
 *mac
 *@author 晓风残月 2020/10/16 09
 *@email itperson@foxmail.com
 */
class MenuBarItem:HiDataItem<Any,HiViewHolder>() {
    private val topTabs = mutableListOf<HiTabTopInfo<Int>>()
    override fun onBindData(holder: HiViewHolder, position: Int) {

        val context = holder.itemView.context?:return
        val menuBar = holder.itemView as HiTabTopLayout
        val list  = mutableListOf<String>("Android","Kotlin","Css","Vue","Java","JS","RN","swift","OC","更多")
        topTabs.clear()
        list.forEachIndexed { index, tabCategory ->
            val defaultColor = ContextCompat.getColor(context, R.color.color_333)
            val selectColor = ContextCompat.getColor(context, R.color.main_color)
            val tabTopInfo = HiTabTopInfo(tabCategory, defaultColor, selectColor)
            topTabs.add(tabTopInfo)
        }
        menuBar.inflateInfo(topTabs as List<HiTabTopInfo<*>>)
        menuBar.defaultSelected(topTabs[0])
        menuBar.addTabSelectedChangeListener(IHiTabLayout.OnTabSelectedListener<HiTabTopInfo<*>> { index, prevInfo, nextInfo ->
            HiLog.d("----",index)
        })
    }

    override fun getItemView(parent: ViewGroup): View? {
        val menuBar = HiTabTopLayout(parent.context)
        val params = LinearLayout.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
        params.bottomMargin = HiDisplayUtil.dp2px(1f)
        menuBar.setPadding(0,HiDisplayUtil.dp2px(3f),0,HiDisplayUtil.dp2px(3f))
        menuBar.layoutParams = params
        menuBar.setBackgroundColor(Color.WHITE)
        return menuBar
    }
}