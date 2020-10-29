package org.qwwccd.q.home.item.search

import android.content.res.ColorStateList
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.search_item_key.*
import org.devio.hi.library.log.HiLog
import org.devio.hi.library.util.HiDisplayUtil
import org.devio.hi.library.util.HiRes
import org.devio.hi.ui.item.HiDataItem
import org.devio.hi.ui.item.HiViewHolder
import org.qwwccd.q.home.R


/**
 *mac
 *@author 晓风残月 2020/10/18 11
 *@email itperson@foxmail.com
 */
class SearchKeyItem(private val type:Int):HiDataItem<Any,HiViewHolder>() {

    var list  = mutableListOf<String>()

    override fun onBindData(holder: HiViewHolder, position: Int) {
        val context = holder.itemView.context?:return

        HiLog.d("-=-=",type)
        if(type==2){
            holder.key_title.text = "搜索发现"
            holder.key_icon.text = "隐藏"
            list  = mutableListOf<String>("Android","Kotlin","Css","Vue","Java","JS","RN","swift","OC","更多")
        }else{
            holder.key_title.text = "历史搜索"
            holder.key_icon.text = "删除"
            list  = mutableListOf<String>("Android","swift","OC","更多")
        }

        for (index in list.indices){
            val chipGroup = holder.key_chip_group
            val chipLabel = if (index<chipGroup.childCount){
                chipGroup.getChildAt(index) as Chip
            }else{
                val chipLabel = Chip(context)
                chipLabel.chipCornerRadius = HiDisplayUtil.dp2px(50f).toFloat()
                chipLabel.chipBackgroundColor =
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            context,
                            R.color.color_faf0
                        )
                    )
                chipLabel.setTextColor(HiRes.getColor(R.color.color_999))
                chipLabel.textSize = 14f
                chipLabel.gravity = Gravity.CENTER
                chipLabel.isCheckedIconVisible = false
                chipLabel.isCheckable = false
                chipLabel.isChipIconVisible = false
                holder.key_chip_group.addView(chipLabel)
                chipLabel
            }
            chipLabel.text = list[index]

        }
    }

    override fun getItemLayoutRes(): Int {
        return R.layout.search_item_key
    }
}