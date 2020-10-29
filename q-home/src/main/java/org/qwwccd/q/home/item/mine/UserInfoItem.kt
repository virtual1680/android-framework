package org.qwwccd.q.home.item.mine

import kotlinx.android.synthetic.main.mine_item_userinfo.*
import org.devio.hi.library.util.setImageRoundUri
import org.devio.hi.ui.item.HiDataItem
import org.devio.hi.ui.item.HiViewHolder
import org.qwwccd.q.home.R


/**
 *mac
 *@author 晓风残月 2020/10/16 16
 *@email itperson@foxmail.com
 */
class UserInfoItem:HiDataItem<Any,HiViewHolder>() {
    override fun onBindData(holder: HiViewHolder, position: Int) {
        val context = holder.itemView.context?:return
        holder.user_img.setImageRoundUri("http://javashop-statics.oss-cn-beijing.aliyuncs.com/v70/null/17AC63E700374E5C89532AB205799869.jpg")
    }

    override fun getItemLayoutRes(): Int {
        return R.layout.mine_item_userinfo
    }


}