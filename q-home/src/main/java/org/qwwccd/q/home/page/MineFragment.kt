package org.qwwccd.q.home.page

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_mine.*
import kotlinx.android.synthetic.main.mine_item_userinfo.*
import org.devio.hi.library.util.MainHandler
import org.devio.hi.library.util.QStatusBar
import org.devio.hi.library.util.reLayout
import org.devio.hi.ui.item.HiAdapter
import org.devio.hi.ui.item.HiDataItem
import org.qwwccd.q.commom.base.QBaseFragment
import org.qwwccd.q.home.R
import org.qwwccd.q.home.databinding.FragmentHomeBinding
import org.qwwccd.q.home.item.mine.ManagerMenuItem
import org.qwwccd.q.home.item.mine.UserInfoItem
import org.qwwccd.q.home.viewModel.HomeViewModel


/**
 *mac
 *@author 晓风残月 2020/10/12 10
 *@email itperson@foxmail.com
 */
class MineFragment:QBaseFragment<HomeViewModel,FragmentHomeBinding>() {

    private val dataItems = mutableListOf<HiDataItem<*,*>>()
    private lateinit var adapter: HiAdapter

    override fun getLayId(): Int {
        return R.layout.fragment_mine
    }

    override fun initVM(): HomeViewModel {
        return ViewModelProvider(this)[HomeViewModel::class.java]
    }
    override fun init() {

//        top_status_height.reLayout<ViewGroup.LayoutParams> { params ->
//            params.height = QStatusBar.getStatusBarHeight()
//        }
//        val findViewById = activity.findViewById<View>(R.id.top_status_height)
//        findViewById.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,QStatusBar.getStatusBarHeight())
//        top_status_height.height = QStatusBar.getStatusBarHeight()
        nav_bar.setBackgroundAlpha(0)
        adapter = HiAdapter(activity)
        hi_recycle.layoutManager = LinearLayoutManager(activity)
        hi_recycle.adapter = adapter
        dataItems.add(UserInfoItem())
        dataItems.add(ManagerMenuItem())
        adapter.clearItems()
        adapter.addItems(dataItems,true)
        MainHandler.postDelay(2000, Runnable{
            content_loading.visibility = View.GONE
        })
    }

    override fun startObserve() {

    }

    override fun destory() {

    }
}