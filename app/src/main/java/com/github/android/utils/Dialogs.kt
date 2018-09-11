package com.github.android.utils

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import com.github.android.R


object Dialogs {

    /**
     * Show messages in alert dialog
     */
    fun showDailog(mContext: Context, message: String) {

        if (mContext != null) {
            val builder = AlertDialog.Builder(mContext)
            builder.setMessage(message)
            builder.setCancelable(true)

            builder.setNeutralButton(
                    mContext.getString(R.string.lbl_ok),
                    DialogInterface.OnClickListener { dialog, id -> dialog.dismiss() })

            val alert = builder.create()
            alert.show()
        }

    }


}