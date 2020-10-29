package org.qwwccd.q.home.item

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import org.devio.hi.library.log.HiLog
import org.devio.hi.library.util.HiDisplayUtil
import org.devio.hi.library.util.loadUrl
import org.devio.hi.ui.banner.HiBanner
import org.devio.hi.ui.banner.core.HiBannerMo
import org.devio.hi.ui.item.HiDataItem
import org.devio.hi.ui.item.HiViewHolder


/**
 *mac
 *@author 晓风残月 2020/10/15 20
 *@email itperson@foxmail.com
 */
class BannerItem:HiDataItem<Any,HiViewHolder>() {

    override fun onBindData(holder: HiViewHolder, position: Int) {
        val context = holder.itemView.context ?: return
        val banner = holder.itemView as HiBanner
        val models = mutableListOf<HiBannerMo>()
        val q = "http://lianjiu.oss-cn-hangzhou.aliyuncs.com/productImagesnormal/36ABEDF2F76C4BD4B3C55A0741F9B3BA.jpeg"
        val w = "http://lianjiu.oss-cn-hangzhou.aliyuncs.com/productImagesnormal/D48A7ED1FB3D40FD8D3E6C712CB58FA7.jpeg"
        val c = "http://lianjiu.oss-cn-hangzhou.aliyuncs.com/productImagesnormal/B8BB9C4D9A434FF489ED2D951614477E.jpeg"
        val d = "http://lianjiu.oss-cn-hangzhou.aliyuncs.com/productImagesnormal/72BEDDBEB45A4CFB9E36379257AA9BB6.jpeg"
        val list = mutableListOf<String>(q, w, c, d)
        list.forEachIndexed { index, homeBanner ->
            val bannerMo = object : HiBannerMo() {}
            bannerMo.url = homeBanner
            models.add(bannerMo)
        }
        banner.setOnBannerClickListener { viewHolder, bannerMo, position ->
            //拿到position，找出list的item
            HiLog.d("-------",position)
        }
        banner.setBannerData(models)
        banner.setBindAdapter { viewHolder, mo, position ->
            ((viewHolder.rootView) as ImageView).loadUrl(mo.url)
        }

    }

    override fun getItemView(parent: ViewGroup): View? {
        val context = parent.context
        val banner = HiBanner(context)
        val params = RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            HiDisplayUtil.dp2px(200f)
        )
        params.bottomMargin = HiDisplayUtil.dp2px(10f)
        banner.layoutParams = params
        banner.setBackgroundColor(Color.WHITE)
        return banner
    }

}