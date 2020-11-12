package com.example.stream_cash.activity.login

import android.content.Context
import android.content.Intent
import com.example.stream_cash.activity.home.HomeActivity
import com.example.stream_cash.activity.register.RegisterActivity
import com.example.stream_cash.util.GlobalString

class LoginRouter(mContext: Context) : LoginContracts.Router {

    private var context = mContext

    override fun keDaftar() {
        context.startActivity(Intent(context, RegisterActivity::class.java))
    }

    override fun keHome(emailUser: String) {
        val sharedPreferences = context.getSharedPreferences(GlobalString.shared, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("emailUser", emailUser)
        editor.apply()
        context.startActivity(Intent(context, HomeActivity::class.java))
    }

}