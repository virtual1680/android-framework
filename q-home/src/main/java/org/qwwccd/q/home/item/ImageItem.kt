package org.qwwccd.q.home.item

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import org.devio.hi.library.util.HiDisplayUtil
import org.devio.hi.library.util.loadUrl
import org.devio.hi.ui.item.HiDataItem
import org.devio.hi.ui.item.HiViewHolder


/**
 *mac
 *@author 晓风残月 2020/10/16 09
 *@email itperson@foxmail.com
 */
class ImageItem:HiDataItem<Any,HiViewHolder>() {
    override fun onBindData(holder: HiViewHolder, position: Int) {
        val context = holder.itemView.context?:return
        val imageView = holder.itemView as ImageView

        imageView.loadUrl("http://javashop-statics.oss-cn-beijing.aliyuncs.com/v70/null/FF080CBC64B74F0E81EE62D899062CC6.jpg")
    }

    override fun getItemView(parent: ViewGroup): View? {
        val image = ImageView(parent.context)
        image.scaleType = ImageView.ScaleType.CENTER_CROP
        image.setBackgroundColor(Color.WHITE)
        image.layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,HiDisplayUtil.dp2px(80f))
        return image
    }
}