package org.qwwccd.q.home.item

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.item_article.*
import org.devio.hi.library.util.HiDisplayUtil
import org.devio.hi.library.util.loadUrl
import org.devio.hi.ui.item.HiDataItem
import org.devio.hi.ui.item.HiViewHolder
import org.qwwccd.q.commom.route.HiRoute
import org.qwwccd.q.home.R


/**
 *mac
 *@author 晓风残月 2020/10/15 20
 *@email itperson@foxmail.com
 */
class ArticleItem(val layoutManager: String = "VERTICAL"):HiDataItem<Any,HiViewHolder>() {
    override fun onBindData(holder: HiViewHolder, position: Int) {
        val context = holder.itemView.context?:return
        val linearView = holder.itemView as RecyclerView
        val list  = mutableListOf<String>("Android","Kotlin","Css","Vue","Java","JS","RN","swift","OC","更多")
        linearView.adapter = ArticleAdapter(context, list,layoutManager)
    }

    override fun getItemView(parent: ViewGroup): View? {
        val linearView = RecyclerView(parent.context)
        val params = RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
        params.bottomMargin = HiDisplayUtil.dp2px(10f)
        if (layoutManager=="HORIZONTAL"){
            linearView.layoutManager = LinearLayoutManager(parent.context,LinearLayoutManager.HORIZONTAL,false)
        }else{
            linearView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        }
        linearView.layoutParams = params
        linearView.setPadding(HiDisplayUtil.dp2px(5f),HiDisplayUtil.dp2px(5f),HiDisplayUtil.dp2px(5f),HiDisplayUtil.dp2px(5f))
        linearView.setBackgroundColor(Color.WHITE)
        return linearView
    }

    inner class ArticleAdapter(private val context:Context, private val list:List<String>,private val layoutManager: String):RecyclerView.Adapter<ArticleAdapter.ArticleItemViewHolder>(){
        private var inflater = LayoutInflater.from(context)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleItemViewHolder {
            val binding:ViewDataBinding
            if (layoutManager=="HORIZONTAL"){
                binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, R.layout.item_article,parent,false)
            }else{
                binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, R.layout.item_article_span_2,parent,false)
            }
            return ArticleItemViewHolder(binding.root, binding)
        }

        override fun onBindViewHolder(holder: ArticleItemViewHolder, position: Int) {
            val subcategory = list[position]
            //holder.binding.setVariable(BR.subCategory, subcategory)
            if (position==0){
                holder.itemView.setOnClickListener {
                    HiRoute.push(context=holder.itemView.context,path = "/detail/root")
                }
                holder.item_image.loadUrl("http://javashop-statics.oss-cn-beijing.aliyuncs.com/v70/null/51F16B8D09B645DF94CCE5E3534B543D.jpg")
            }else if (position==3){
//                holder.item_image.layoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,HiDisplayUtil.dp2px(50f))
                holder.item_image.loadUrl("http://javashop-statics.oss-cn-beijing.aliyuncs.com/v70/null/2BCC29DE3BE44DDFAEC0C1104C37ED41.jpg")
            }else if (position==4){
                holder.item_image.loadUrl("http://javashop-statics.oss-cn-beijing.aliyuncs.com/v70/null/010B50AED5054153B6FD68933A1268D3.jpg")
            }else{
                holder.item_image.loadUrl("http://javashop-statics.oss-cn-beijing.aliyuncs.com/v70/null/2CBA43470D78462A903E53731252798F.jpg")
            }
            holder.item_title.text = "晓风残月敬请期待份大奖是开放开了"
            holder.item_type.text = subcategory
            holder.item_look.text = "123"
        }
        override fun getItemCount(): Int {
            return list.size
        }


        inner class ArticleItemViewHolder(view: View, val binding: ViewDataBinding) : HiViewHolder(view) {}
    }

}