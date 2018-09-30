package com.example.hippo.simpledemo.ui.base.presenter

import com.example.hippo.simpledemo.ui.base.view.MvpView

import java.lang.ref.Reference
import java.lang.ref.WeakReference

abstract class BaseMVPPresenter<T : MvpView> {
    /**
     * Reference of View's interface
     */
    private var mViewRef: Reference<T>? = null

    protected lateinit var mMvpView: T

    /**
     * get View
     * @return View
     */
    val view: T
        get() = mViewRef!!.get()!!

    /**
     *
     * todo : only when  isActivityAlive get true, then you can do something
     * eg: Dialog、Window、 jumb to Activity.
     *
     * @return boolean
     */
    val isViewAttached: Boolean
        get() = mViewRef != null && mViewRef!!.get() != null

    /**
     * attach view
     */
    fun attachView(view: T) {
        mViewRef = WeakReference(view)
        if (isViewAttached) {
            mMvpView = view
        }
    }

    /**
     * detach view
     */
    fun detachView() {
        if (mViewRef != null) {
            mViewRef!!.clear()
            mViewRef = null
        }
    }
}
