package com.example.hippo.simpledemo.ui.base.view

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast

import com.example.hippo.simpledemo.ui.base.presenter.BaseMVPPresenter
import me.yokeyword.fragmentation.SupportActivity

abstract class BaseMVPActivity<V : MvpView, T : BaseMVPPresenter<V>> : SupportActivity(), MvpView {

    /**
     * Presenter object
     */
    var mPresenter: T? = null
    var mProgress: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = createPresenter()
        mPresenter!!.attachView(this as V)
        mProgress = ProgressDialog(this)
        mProgress!!.setMessage("loading...")
    }

    override fun onDestroy() {
        if (mPresenter != null) {
            mPresenter!!.detachView()
        }
        super.onDestroy()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (mPresenter == null) {
            mPresenter = createPresenter()
        }
    }

    /**
     * create Presenter object
     * @return Presenter object
     */
    protected abstract fun createPresenter(): T

    override fun getContext(): Context {
        return this
    }

    fun getPresenter() : T {
        return mPresenter!!
    }

    override fun showProgress() {
        if (mProgress != null && !mProgress!!.isShowing) {
            mProgress!!.show()
        }
    }

    override fun closeProgress() {
        if (mProgress != null && mProgress!!.isShowing) {
            mProgress!!.dismiss()
        }
    }

    override fun showToast(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }
}