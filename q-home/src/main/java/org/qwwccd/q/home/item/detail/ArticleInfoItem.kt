package org.qwwccd.q.home.item.detail

import kotlinx.android.synthetic.main.detail_item_article_info.*
import org.devio.hi.library.util.loadUrl
import org.devio.hi.ui.item.HiDataItem
import org.devio.hi.ui.item.HiViewHolder
import org.qwwccd.q.home.R


/**
 *mac
 *@author 晓风残月 2020/10/17 20
 *@email itperson@foxmail.com
 */
class ArticleInfoItem:HiDataItem<Any,HiViewHolder>() {
    override fun onBindData(holder: HiViewHolder, position: Int) {
        val context = holder.itemView.context?:return

        holder.article_img.loadUrl("http://javashop-statics.oss-cn-beijing.aliyuncs.com/v70/null/58079FD5E799440A970703C2B8FE105E.jpg")
    }

    override fun getItemLayoutRes(): Int {
        return R.layout.detail_item_article_info
    }
}