package org.qwwccd.q.home.item.detail

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.recyclerview.widget.RecyclerView
import org.devio.hi.library.util.QHtmlUtils
import org.devio.hi.ui.item.HiDataItem
import org.devio.hi.ui.item.HiViewHolder


/**
 *mac
 *@author 晓风残月 2020/10/17 22
 *@email itperson@foxmail.com
 */
class WebViewItem:HiDataItem<Any,HiViewHolder>() {
    val data = "<p>这是一个使用RecyclerView实现瀑布流，并带上下拉刷新和上拉加载功能的Demo。做Demo之前看了很多网友们实现瀑布流踩的坑，所以这个Demo把那些常见的坑都填上了，目前没发现有什么问题。"+
            "<img src='http://javashop-statics.oss-cn-beijing.aliyuncs.com/v70/null/E5778C1272EC4CEE8145E6B0BE757B47.jpg' />"+
            "关于下拉刷新和上拉加载，说实话我每次做这个功能都很头疼，因为一直没有找到一个好的方式或者说好的库，能让我只拿着一个框架就去适配所有需要下拉刷新和上拉加载功能的ViewGroup，并且可以自己实"+
            "现Header和Footer。今天自己鼓捣了很久瀑布流的Header和Footer,然而光是Footer我就花费了不少功夫，最后在寻找好的Header实现方式的过程中发现了一个能满足我全部需求的库<br/><br/>"+
            "<img src='http://javashop-statics.oss-cn-beijing.aliyuncs.com/v70/null/F9F08D8EB4CA491C8DFDABDE9D79F4BE.jpg' /><br/><br/>"+
            "讲真，我以前就见过这个库，但是那时候觉得这个库花里胡哨的就没仔细看下去，今天定睛一看，，我的妈呀，神器呀，我以前咋就把它错过了呢！从此我要抱着这个库不放手了。"+
            "废话了这么多，下边来看看我的实现吧。</p>" +
            "<img style='cursor: zoom-in;'  src='http://javashop-statics.oss-cn-beijing.aliyuncs.com/v70/null/CCDC71EF20AF424D8F4AEBBA91C8BE5F.jpg'/><br/>"

    val string = QHtmlUtils.get.fitImageSize(data)

    override fun onBindData(holder: HiViewHolder, position: Int) {
        val context = holder.itemView.context?:return
        val itemView = holder.itemView as WebView
        val webViewSetting = itemView.settings
        webViewSetting.useWideViewPort = true
        webViewSetting.loadWithOverviewMode = true
        webViewSetting.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
        webViewSetting.javaScriptEnabled = true
        webViewSetting.setSupportZoom(true)
        webViewSetting.builtInZoomControls = true
        itemView.isVerticalScrollBarEnabled = false
        itemView.isHorizontalScrollBarEnabled = false
        itemView.loadData(string,"text/html; charset=UTF-8",null)
    }

    override fun getItemView(parent: ViewGroup): View? {
        val webView = WebView(parent.context)
        webView.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,RecyclerView.LayoutParams.WRAP_CONTENT)
        return webView
    }


}