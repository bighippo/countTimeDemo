package com.example.hippo.simpledemo.widget

import android.app.Dialog
import android.content.Context
import com.example.hippo.simpledemo.R

object LoadingDialog {
    private  var dialog: Dialog? = null

    fun show(context: Context) {
        cancel()
        dialog = Dialog(context, R.style.LoadingDialog)
        dialog?.setContentView(R.layout.dialog_loading)
        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.show()
    }

    fun cancel() {
        dialog?.dismiss()
    }
}