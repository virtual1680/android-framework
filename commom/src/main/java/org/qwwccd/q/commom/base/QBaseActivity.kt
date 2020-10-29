package org.qwwccd.q.commom.base

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.alibaba.android.arouter.launcher.ARouter
import org.qwwccd.q.commom.route.HiRoute

/**
 * @Note   Activity基类
 *
 * 需要生命周期监听则继承 BaseControl
 */
abstract class QBaseActivity<MViewModel : QBaseViewModel,MViewBinding: ViewDataBinding> : QBaseToolActivity() {

    protected lateinit var mViewModel: MViewModel
    protected lateinit var mViewBinding: MViewBinding

    /**
     * @Note   Activity创建时进行相关的配置
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        HiRoute.inject(this)
        /**父类初始化*/
        super.onCreate(savedInstanceState)
        mViewModel = initVM()
        /**创建根视图 初始化Databinding对象*/
       /// val rootView = layoutInflater.inflate(getLayoutId(), null, false)
        mViewBinding = DataBindingUtil.setContentView(this,getLayoutId())
        mViewBinding.lifecycleOwner =this
        initView()
        initData()
        startObserve()
    }


    /**
     * @Note   Activity销毁回调
     */
    override fun onDestroy() {
        super.onDestroy()
        /**DataBinding解除绑定*/
        mViewBinding.unbind()
        /**执行抽象方法destory()*/
        destory()
        Log.e("PageDestory","页面销毁======>$localClassName")
    }


    open fun getLayoutId(): Int = 0
    abstract fun initVM(): MViewModel
    abstract fun initView()
    abstract fun initData()
    abstract fun startObserve()
    abstract fun destory()


    /**
     * @Note   页面返回数据
     * @param  resultCode 返回码
     * @param  data       数据
     */
    open fun resultHandle(resultCode: Int,data: Intent?){ }
    /**
     * @Note   页面返回数据回调
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        resultHandle(resultCode,data)
    }
}