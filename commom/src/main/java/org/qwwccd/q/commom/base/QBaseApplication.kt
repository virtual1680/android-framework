package org.qwwccd.q.commom.base

import android.app.Application
import com.google.gson.Gson
import org.devio.hi.library.log.HiConsolePrinter
import org.devio.hi.library.log.HiFilePrinter
import org.devio.hi.library.log.HiLogConfig
import org.devio.hi.library.log.HiLogManager
import org.devio.hi.library.util.ActivityManager

/**
 * mac
 *
 * @author 晓风残月 2020/10/12 09
 * @email itperson@foxmail.com
 */
open class QBaseApplication:Application(){
    override fun onCreate() {
        super.onCreate()
        initLog()
    }

    private fun initLog() {
        HiLogManager.init(object : HiLogConfig() {
            override fun injectJsonParser(): JsonParser {
                return JsonParser { src: Any? -> Gson().toJson(src) }
            }

            override fun includeThread(): Boolean {
                return true
            }
        }, HiConsolePrinter(), HiFilePrinter.getInstance(cacheDir.absolutePath, 0))
        ActivityManager.instance.init(this)
    }
}