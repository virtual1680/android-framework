package org.qwwccd.q.commom.bind

import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.marginTop
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import org.devio.hi.library.util.reLayout
import org.devio.hi.library.util.QScreenTool
import org.devio.hi.library.util.QStatusBar


/**
 *
 * @author 晓风残月  2020/7/21 14:12
 * @email 495301515@qq.com
 */
object DataBindingHelper {

    /**
     * 获取标题栏总高度
     */
    @BindingAdapter(value = ["bind:auto_topbar_height"], requireAll = true)
    @JvmStatic
    fun topbarAutoHeight(view : View, type :Boolean){
        view.reLayout<ViewGroup.LayoutParams> { params ->
            params.height = (QStatusBar.getStatusBarHeight() + QScreenTool.getScreenWidth(view.context) * 0.13).toInt()
        }
    }

    @BindingAdapter(value = ["bind:margin_top_nav_height"], requireAll = true)
    @JvmStatic
    fun marginTopNavH(view : View, type :Boolean){
        view.reLayout<LinearLayout.LayoutParams> { params ->
            params.topMargin = (QStatusBar.getStatusBarHeight() + QScreenTool.getScreenWidth(view.context) * 0.13).toInt()
//            view.marginTop = (QStatusBar.getStatusBarHeight() + QScreenTool.getScreenWidth(view.context) * 0.13).toInt()
        }
    }

    /**
     * 获取状态栏高度
     */
    @BindingAdapter(value = ["bind:app_status_height"], requireAll = true)
    @JvmStatic
    fun appStatusHeight(view:View,type: Boolean){
        view.reLayout<ViewGroup.LayoutParams> { params ->
            params.height = QStatusBar.getStatusBarHeight()
        }
    }

    /**
     * 设置网络图片 url 默认填充 error
     */
    @BindingAdapter(value = ["url", "error"], requireAll = false)
    @JvmStatic
    fun setImageUri(view: ImageView, url: String?, placeholderRes: Int) {
        if (!TextUtils.isEmpty(url)) {
            Glide.with(view.context).load(url).placeholder(placeholderRes).into(view)
        }
    }

}