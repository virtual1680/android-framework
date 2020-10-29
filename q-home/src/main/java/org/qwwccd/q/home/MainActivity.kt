package org.qwwccd.q.home

import android.content.Intent
import android.os.Bundle
import org.qwwccd.q.commom.base.QBaseToolActivity


/**
 *mac
 *@author 晓风残月 2020/10/12 10
 *@email itperson@foxmail.com
 */
class MainActivity:QBaseToolActivity(), MainActivityLogic.ActivityProvider {

    private var activityLogic: MainActivityLogic? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        homePageDo()//是首页需要加载此方法
        activityLogic = MainActivityLogic(this,savedInstanceState)
//        val channel: String = ChannelReaderUtil.getChannel(this)
        //String channel = ChannelReaderUtil.getChannel(getApplicationContext());
        //Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        //String channel = ChannelReaderUtil.getChannel(getApplicationContext());
        //Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
//        showMessage("MainActivity:$channel")

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        activityLogic!!.onSaveInstanceState(outState)
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        val fragments = supportFragmentManager.fragments
        for (fragment in fragments) {
            fragment.onActivityResult(requestCode, resultCode, data)
        }
    }

}