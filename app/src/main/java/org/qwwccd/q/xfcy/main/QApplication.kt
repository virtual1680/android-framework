package org.qwwccd.q.xfcy.main

import android.content.Context
import androidx.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.tencent.bugly.Bugly
import dagger.hilt.android.HiltAndroidApp
import org.devio.hi.library.BuildConfig
import org.qwwccd.q.commom.base.QBaseApplication


/**
 *mac
 *@author 晓风残月 2020/10/12 09
 *@email itperson@foxmail.com
 */
@HiltAndroidApp
class QApplication: QBaseApplication() {


    override fun onCreate() {
        super.onCreate()
        initArouter()
        initBugly()

        initSdk()
    }

    private fun initBugly() {
//        Bugly.init(this, "10d86971eb", true)
        Bugly.setIsDevelopmentDevice(this, true)
    }

    private fun initArouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }

        ARouter.init(this)
    }

    private fun initSdk(){
        // 设置全局的 Header 构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context: Context?, layout: RefreshLayout? ->
            ClassicsHeader(context).setEnableLastTime(false)
        }
        // 设置全局的 Footer 构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context: Context?, layout: RefreshLayout? ->
            ClassicsFooter(context).setDrawableSize(20f)
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }

}