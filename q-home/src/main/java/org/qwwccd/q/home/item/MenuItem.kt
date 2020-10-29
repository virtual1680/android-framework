package org.qwwccd.q.home.item

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_menu.*
import org.devio.hi.library.util.HiDisplayUtil
import org.devio.hi.library.util.loadUrl
import org.devio.hi.ui.item.HiDataItem
import org.devio.hi.ui.item.HiViewHolder
import org.qwwccd.q.home.R


/**
 *mac
 *@author 晓风残月 2020/10/15 20
 *@email itperson@foxmail.com
 */
class MenuItem:HiDataItem<Any,HiViewHolder>() {
    override fun onBindData(holder: HiViewHolder, position: Int) {
        val context = holder.itemView.context?:return
        val gridView = holder.itemView as RecyclerView
        val list  = mutableListOf<String>("Android","Kotlin","Css","Vue","Java","JS","RN","swift","OC","更多")
        gridView.adapter = MenuAdapter(context, list)
    }

    override fun getItemView(parent: ViewGroup): View? {
        val gridView = RecyclerView(parent.context)
        val params = RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
        params.bottomMargin = HiDisplayUtil.dp2px(10f)
        gridView.layoutManager = GridLayoutManager(parent.context, 5)
        gridView.layoutParams = params
        gridView.setPadding(0,HiDisplayUtil.dp2px(5f),0,0)
        gridView.setBackgroundColor(Color.WHITE)
        return gridView
    }

    inner class MenuAdapter(private val context:Context, private val list:List<String>):RecyclerView.Adapter<MenuAdapter.MenuItemViewHolder>(){
        private var inflater = LayoutInflater.from(context)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
            val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, R.layout.item_menu,parent,false)
            return MenuItemViewHolder(binding.root, binding)
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
            val subcategory = list[position]
            //holder.binding.setVariable(BR.subCategory, subcategory)

            holder.item_image.loadUrl("http://javashop-statics.oss-cn-beijing.aliyuncs.com/v70/null/2BCC29DE3BE44DDFAEC0C1104C37ED41.jpg")
            holder.item_title.text = subcategory
        }

        inner class MenuItemViewHolder(view: View, val binding: ViewDataBinding) : HiViewHolder(view) {}
    }

}