package org.qwwccd.q.home.item.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.search_hot_item.*
import kotlinx.android.synthetic.main.search_hot_list_item.*
import org.devio.hi.ui.item.HiAdapter
import org.devio.hi.ui.item.HiDataItem
import org.devio.hi.ui.item.HiViewHolder
import org.devio.hi.ui.recyclerview.HiRecyclerView
import org.qwwccd.q.home.R


/**
 *mac
 *@author 晓风残月 2020/10/20 21
 *@email itperson@foxmail.com
 */

class HotSearchListItem :HiDataItem<Any,HiViewHolder>(){

    val list = mutableListOf<String>("发动机两款发动机今飞凯达","积分第六届今飞凯达积分","九分裤来得及啊减肥的","分搭建看了发来九分裤","发动机两款发动机今飞凯达","积分第六届今飞凯达积分","九分裤来得及啊减肥的","分搭建看了发来九分裤")

    override fun onBindData(holder: HiViewHolder, position: Int) {
        val context = holder.itemView.context?:return
        holder.hot_recycler.layoutManager = LinearLayoutManager(context)
        holder.hot_recycler.adapter = HotListItemAdapter(context,list)
    }

    override fun getItemLayoutRes(): Int {
        return R.layout.search_hot_list_item
    }

    inner class HotListItemAdapter(private val context:Context,private val list:List<String>):RecyclerView.Adapter<HotListItemAdapter.HotListItemHolder>(){
        private var inflater = LayoutInflater.from(context)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotListItemAdapter.HotListItemHolder {
            val binding:ViewDataBinding = DataBindingUtil.inflate(inflater,R.layout.search_hot_item,parent,false)
            return HotListItemHolder(binding.root, binding)
        }

        override fun getItemCount(): Int {
           return list.size
        }

        override fun onBindViewHolder(holder: HotListItemAdapter.HotListItemHolder, position: Int) {
            val data = list[position]
            holder.hot_item_sort.text = (position+1).toString()
            holder.hot_title.text = data
        }

        inner class HotListItemHolder(view:View,val binding: ViewDataBinding):HiViewHolder(view){}

    }

}