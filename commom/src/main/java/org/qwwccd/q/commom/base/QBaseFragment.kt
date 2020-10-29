package org.qwwccd.q.commom.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * Fragment基类
 */
  abstract class QBaseFragment<MViewModel : QBaseViewModel, DataBindingType : ViewDataBinding> : Fragment() {

    /**
     * View根视图
     */
    private var layout: View? = null

    /**
     * @Note  Fragment宿主Activity
     */
    protected lateinit var activity: QBaseToolActivity

    protected lateinit var mViewDataBinding: DataBindingType

    protected lateinit var mViewModel: MViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        /**初始化宿主Activity*/
        activity = getActivity() as QBaseToolActivity
        if (layout == null) {
            layout = inflater.inflate(getLayId(), null)
            mViewDataBinding = DataBindingUtil.bind(layout!!)!!
        }
        return layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        mViewModel = initVM()
        mViewDataBinding.lifecycleOwner = this
        init()
        startObserve()
        super.onViewCreated(view, savedInstanceState)
    }

    //检测 宿主 是否还存活
    open fun isAlive(): Boolean {
        return !(isRemoving || isDetached || getActivity() == null)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mViewDataBinding.unbind() /**ViewBinding解除绑定*/
        destory() /**执行其他销毁操作*/
        Log.e("FragmentDestory","页面销毁======>${javaClass.name}")
    }

    protected abstract fun getLayId(): Int

    abstract fun initVM(): MViewModel

    protected abstract fun init()

    protected abstract fun startObserve()

    protected abstract fun destory()
    /**
     * @Note   获取工具对象
     * @return 工具对象
     */
    protected fun getUtils(): QBaseInterface {
        return activity
    }
}