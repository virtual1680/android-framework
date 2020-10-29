package org.qwwccd.q.home.page

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.ContentLoadingProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home.*
import org.devio.hi.library.log.HiLog
import org.devio.hi.library.util.HiDisplayUtil
import org.devio.hi.library.util.MainHandler
import org.devio.hi.library.util.QScreenTool
import org.devio.hi.ui.empty.EmptyView
import org.devio.hi.ui.item.HiAdapter
import org.devio.hi.ui.item.HiDataItem
import org.devio.hi.ui.recyclerview.HiRecyclerView
import org.devio.hi.ui.refresh.HiOverView
import org.devio.hi.ui.refresh.HiRefresh
import org.devio.hi.ui.refresh.HiRefreshLayout
import org.devio.hi.ui.refresh.HiTextOverView
import org.devio.hi.ui.tab.common.IHiTabLayout
import org.devio.hi.ui.tab.top.HiTabTopInfo
import org.qwwccd.q.commom.base.QBaseFragment
import org.qwwccd.q.commom.route.HiRoute
import org.qwwccd.q.home.R
import org.qwwccd.q.home.databinding.FragmentHomeBinding
import org.qwwccd.q.home.item.*
import org.qwwccd.q.home.viewModel.HomeViewModel

/**
 *mac
 *@author 晓风残月 2020/10/12 10
 *@email itperson@foxmail.com
 */
class HomeFragment:QBaseFragment<HomeViewModel,FragmentHomeBinding>(), HiRefresh.HiRefreshListener {

    var pageIndex: Int = 1
    private lateinit var adapter: HiAdapter
    private lateinit var refreshHeaderView: HiTextOverView
    private var loadingView: ContentLoadingProgressBar? = null
    private var emptyView: EmptyView? = null
    private var recyclerView: HiRecyclerView? = null
    private var refreshLayout: HiRefreshLayout? = null
    val dataItems = mutableListOf<HiDataItem<*, *>>()
    var mmRvScrollY = 0 // 列表滑动距离


    private val topTabs = mutableListOf<HiTabTopInfo<Int>>()

    override fun getLayId(): Int {
        return R.layout.fragment_home
    }

    override fun initVM(): HomeViewModel {
        return ViewModelProvider(this)[HomeViewModel::class.java]
    }

    @SuppressLint("ResourceAsColor")
    override fun init() {
        this.refreshLayout = refresh_layout
        this.recyclerView = hi_recycle
        this.emptyView = empty_view
        this.loadingView = content_loading

        refreshHeaderView = HiTextOverView(context)
        refreshLayout?.setRefreshOverView(refreshHeaderView)
        refreshLayout?.setRefreshListener(this)

        loadingView?.visibility = View.VISIBLE


        val search = LayoutInflater.from(context).inflate(R.layout.search_iconfont_view,null)
        search.layoutParams = LinearLayout.LayoutParams((QScreenTool.getScreenWidth(activity)*0.7).toInt(),HiDisplayUtil.dp2px(30f))
        search.setOnClickListener {
            HiRoute.push(context = activity, path = "/search/root")
        }
        nav_bar.setCenterView(search)
        nav_bar.addRightTextButton("00").setOnClickListener {
            HiLog.d("-=-=-=222")
        }

        adapter = HiAdapter(activity)
        hi_recycle.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        hi_recycle.adapter = adapter



        dataItems.add(BannerItem())
        dataItems.add(MenuItem())
        dataItems.add(ArticleItem("HORIZONTAL"))
        dataItems.add(ImageItem())
        dataItems.add(MenuBarItem())
        val item = MenuItemDecoration()
        hi_recycle.addItemDecoration(item)
        dataItems.add(ArticleItem())

        val list  = mutableListOf<String>("Android","Kotlin","Css","Vue","Java","JS","RN","swift","OC","更多")
        topTabs.clear()
        list.forEachIndexed { index, tabCategory ->
            val defaultColor = ContextCompat.getColor(activity, R.color.color_333)
            val selectColor = ContextCompat.getColor(activity, R.color.main_color)
            val tabTopInfo = HiTabTopInfo(tabCategory, defaultColor, selectColor)
            topTabs.add(tabTopInfo)
        }
        menu_tab_bar.inflateInfo(topTabs as List<HiTabTopInfo<*>>)
        menu_tab_bar.defaultSelected(topTabs[0])
        menu_tab_bar.addTabSelectedChangeListener(IHiTabLayout.OnTabSelectedListener<HiTabTopInfo<*>> { index, prevInfo, nextInfo ->
            HiLog.d("=====",index)
        })

        item.StickyItemDecorator(object :MenuItemDecoration.SortShowListener{
            override fun showSort(show: Boolean) {
                if (show){
                    menu_tab_bar.visibility = View.VISIBLE
                    menu_tab_bar_line.visibility = View.VISIBLE
                }else{
                    menu_tab_bar.visibility = View.GONE
                    menu_tab_bar_line.visibility = View.GONE
                }

            }
        })

        adapter.clearItems()
        adapter.addItems(dataItems,true)
        MainHandler.postDelay(2000, Runnable{
            loadingView?.visibility = View.GONE
        })
    }

    override fun startObserve() {
        recyclerView!!.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                mmRvScrollY += dy
                val alpha = (mmRvScrollY / 1.5).toInt()
                //当alpha=255时  scrollY约等于383 也就是255的1.5倍
                if (alpha<=255){
                    if (alpha<=0){
                        nav_bar.setBackgroundAlpha(0)
                    }else{
                        nav_bar.setBackgroundAlpha(alpha)
                    }
                }else{
                    nav_bar.setBackgroundAlpha(255)
                }
            }
        })
    }

    fun finishRefresh(dataItems: List<HiDataItem<*, out RecyclerView.ViewHolder>>?) {
        val success = dataItems != null && dataItems.isNotEmpty()
        //光真么判断还是不行的，我们还需要别的措施。。。因为可能会出现 下拉单时候，有执行了删上拉分页
        val refresh = pageIndex == 1
        if (refresh) {
            loadingView?.visibility = View.GONE
            refreshLayout?.refreshFinished()
            if (success) {
                emptyView?.visibility = View.GONE
                adapter.clearItems()
                adapter.addItems(dataItems!!, true)
            } else {
                //此时就需要判断列表上是否已经有数据，如果么有，显示出空页面转态
                if (adapter.itemCount <= 0) {
                    emptyView?.visibility = View.VISIBLE
                }
            }
        } else {
            if (success) {
                adapter.addItems(dataItems!!, true)
            }
            recyclerView?.loadFinished(success)
        }
    }
    fun enableLoadMore(callback: () -> Unit) {
        //这里可以直接这么写吗？
        //为了防止 同时 下拉刷新 和上拉分页的请求，这里就需要处理一把
        recyclerView?.enableLoadMore({
            if (refreshHeaderView.state == HiOverView.HiRefreshState.STATE_REFRESH) {
                //正处于刷新状态
                recyclerView?.loadFinished(false)
                return@enableLoadMore
            }
            pageIndex++
            callback()
        }, 5)
    }
    fun disableLoadMore() {
        recyclerView?.disableLoadMore()
    }

    override fun enableRefresh(): Boolean {
        return true
    }

    override fun onRefresh() {
        if (recyclerView?.isLoading() == true) {
            //正处于分页
            //复现场景,比较难以复现---》如果下执行上拉分页。。。快速返回  往下拉，松手。会出现一个bug: 转圈圈的停住不动了。
            //问题的原因在于 立刻调用 refreshFinished 时，refreshHeader的底部bottom值是超过了 它的height的。
            //refreshLayout#recover（dis） 方法中判定了，如果传递dis 参数 大于 header height ,dis =200,height =100,只能恢复到 刷新的位置。不能恢复到初始位置。
            //加了延迟之后，他会  等待 松手的动画做完，才去recover 。此时就能恢复最初状态了。
            refreshLayout?.post {
                refreshLayout?.refreshFinished()
            }
            return
        }
        pageIndex = 1
        MainHandler.postDelay(2000, Runnable{
            finishRefresh(dataItems)
        })
    }

    override fun destory() {

    }


}