package com.example.stream_cash.activity.home

import android.content.Context
import android.content.Intent
import com.example.stream_cash.activity.login.LoginActivity
import com.example.stream_cash.activity.topuptrans.TopUpTransferActivity

class HomeRouter(mContext: Context) : HomeContracts.Router {

    private var context = mContext


    override fun keHalamanTopTrans(tipe:String) {
        val inte = Intent(context, TopUpTransferActivity::class.java)
        inte.putExtra("tipe", tipe)
        context.startActivity(inte)
    }

}