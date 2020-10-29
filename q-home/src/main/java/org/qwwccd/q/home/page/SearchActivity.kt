package org.qwwccd.q.home.page

import android.text.Editable
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import kotlinx.android.synthetic.main.activity_search.*
import org.devio.hi.library.log.HiLog
import org.devio.hi.library.util.HiDisplayUtil
import org.devio.hi.library.util.HiRes
import org.devio.hi.ui.item.HiAdapter
import org.devio.hi.ui.item.HiDataItem
import org.devio.hi.ui.search.HiSearchView
import org.devio.hi.ui.search.SimpleTextWatcher
import org.qwwccd.q.commom.base.QBaseActivity
import org.qwwccd.q.home.R
import org.qwwccd.q.home.databinding.ActivitySearchBinding
import org.qwwccd.q.home.item.search.HotSearchListItem
import org.qwwccd.q.home.item.search.SearchKeyItem
import org.qwwccd.q.home.viewModel.HomeViewModel


/**
 *mac
 *@author 晓风残月 2020/10/18 10
 *@email itperson@foxmail.com
 */
@Route(path = "/search/root")
class SearchActivity:QBaseActivity<HomeViewModel,ActivitySearchBinding>() {

    private lateinit var searchButton: Button
    private lateinit var searchView: HiSearchView

    private lateinit var adapter:HiAdapter
    private val dataItems = mutableListOf<HiDataItem<*,*>>()

    override fun initVM(): HomeViewModel {
        return ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_search
    }

    override fun initView() {
        initTopBar()
        initSearchKey()
    }

    private fun initSearchKey() {
        adapter = HiAdapter(this)
        container.layoutManager = LinearLayoutManager(this)
        container.adapter = adapter
        val ll = SearchKeyItem(1)
        val rr = SearchKeyItem(2)
        dataItems.add(ll)
        dataItems.add(rr)
        dataItems.add(HotSearchListItem())
        adapter.addItems(dataItems,true)
    }

    private fun initTopBar() {
        search_bar.setNavBack {
            onBackPressed()
        }
        searchButton = search_bar.addRightTextButton("搜索")
        searchButton.setTextColor(HiRes.getColorStateList(R.color.color_nav_item_search))
        searchButton.setOnClickListener {
            HiLog.d("-=-=-=-=-=","begin search")
        }
        searchButton.isEnabled = false

        searchView = HiSearchView(this)
        searchView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            HiDisplayUtil.dp2px(33f)
        )
        searchView.setHintText("请输入相关关键字")
        searchView.setClearIconClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {

            }
        })
        searchView.setDebounceTextChangedListener(object : SimpleTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                val hasContent = s != null && s.trim().isNotEmpty()
                searchButton.isEnabled = hasContent
                if (hasContent) {

                } else if (TextUtils.isEmpty(searchView.getKeyword())) {
                    //当输入框内容 被回退删除,且没有高亮的关键词时，回退页面状态

                }
            }
        })
        search_bar.setCenterView(searchView)
    }

    override fun initData() {

    }

    override fun startObserve() {

    }

    override fun destory() {

    }
}