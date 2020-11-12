package com.example.stream_cash.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.example.stream_cash.R

class Function {

    lateinit var dialogProgress:Dialog
    fun showProgress(mContext:Context){
        dialogProgress = Dialog(mContext)
        dialogProgress.setContentView(R.layout.dialog_progres)
        dialogProgress.setCancelable(false)
        dialogProgress.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialogProgress.show()
    }
    fun dismisDialog(){
        dialogProgress.dismiss()
    }
}