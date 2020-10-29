package org.qwwccd.q.home.page

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import kotlinx.android.synthetic.main.activity_detail.*
import org.devio.hi.library.util.MainHandler
import org.devio.hi.ui.item.HiAdapter
import org.devio.hi.ui.item.HiDataItem
import org.qwwccd.q.commom.base.QBaseActivity
import org.qwwccd.q.home.R
import org.qwwccd.q.home.databinding.ActivityDetailBinding
import org.qwwccd.q.home.item.detail.ArticleInfoItem
import org.qwwccd.q.home.item.detail.WebViewItem
import org.qwwccd.q.home.viewModel.HomeViewModel


/**
 *mac
 *@author 晓风残月 2020/10/17 20
 *@email itperson@foxmail.com
 */
@Route(path = "/detail/root")
class DetailActivity:QBaseActivity<HomeViewModel,ActivityDetailBinding>() {

    private var mmRvScrollY = 0 // 列表滑动距离
    private val dataItems = mutableListOf<HiDataItem<*,*>>()
    private lateinit var adapter:HiAdapter

    override fun initVM(): HomeViewModel {
        return ViewModelProvider(this)[HomeViewModel::class.java]
    }
    override fun getLayoutId(): Int {
        return R.layout.activity_detail
    }
    override fun initView() {
        detail_bar.setTitle("文章详情")
        detail_bar.setNavBack {
            onBackPressed()
        }

        adapter = HiAdapter(this)
        detail_recycler.layoutManager = LinearLayoutManager(this)
        detail_recycler.adapter = adapter
        dataItems.add(ArticleInfoItem())
        dataItems.add(WebViewItem())
        adapter.addItems(dataItems,true)
        MainHandler.postDelay(1000, Runnable{
            content_loading.visibility = View.GONE
        })

    }

    override fun initData() {

    }

    override fun startObserve() {

        detail_recycler!!.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                mmRvScrollY += dy
                val alpha = (mmRvScrollY / 1.5).toInt()
                //当alpha=255时  scrollY约等于383 也就是255的1.5倍
                if (alpha<=255){
                    if (alpha<=0){
                        detail_bar.setBackgroundAlpha(0)
                    }else{
                        detail_bar.setBackgroundAlpha(alpha)
                    }
                }else{
                    detail_bar.setBackgroundAlpha(255)
                }
            }
        })

    }

    override fun destory() {

    }
}