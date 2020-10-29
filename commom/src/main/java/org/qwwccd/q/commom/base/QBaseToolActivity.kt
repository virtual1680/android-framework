package org.qwwccd.q.commom.base

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import org.devio.hi.library.util.QStatusBar
import org.qwwccd.q.commom.R

/**
 *
 * @author mac  2020/7/13 11:57
 */

abstract class QBaseToolActivity : AppCompatActivity(), QBaseInterface {
    /**
     * acivity
     */
    protected var activity: QBaseToolActivity? = null

    /**
     * 滑动退出View
     */

    private var isHomePage = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = this
        initTool()
    }

    private fun initTool() {
        setAppStatusBar(null)
    }

    override fun onBackPressed() {
        toolBack()
    }

    /**
     * 工具Activity自带退出
     */
    protected fun toolBack() {
        if (isHomePage) {
            val localIntent = Intent("android.intent.action.MAIN")
            localIntent.addCategory("android.intent.category.HOME")
            startActivity(localIntent)
            return
        }
        finish()
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out)
    }

    @SuppressLint("ResourceType")
    protected fun setAppStatusBar(@ColorInt color:Int?){
       QStatusBar.setStatusBar(
           this,
           false,
           color?:Color.parseColor("#1A000000"),
           true
       )
    }

    protected fun homePageDo() {
        isHomePage = true
    }
}