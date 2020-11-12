package com.example.stream_cash.activity.register

import android.content.Context
import android.content.Intent
import com.example.stream_cash.activity.login.LoginActivity

class RegisterRouter(mContext: Context) : RegisterContracts.Router {

    private var context = mContext

    override fun keLogin() {
        context.startActivity(Intent(context, LoginActivity::class.java))
    }

}