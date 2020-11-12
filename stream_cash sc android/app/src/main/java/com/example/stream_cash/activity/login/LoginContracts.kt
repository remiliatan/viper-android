package com.example.stream_cash.activity.login

class LoginContracts {

    interface View{
        fun onDestroy()
        fun showToast(message:String)
    }

    interface Interaksi{
        fun prosesLogin(emailUser: String, passwordUser: String)
    }

    interface Presenter{
        fun tombolLoginKlik(emailUser:String, passwordUser:String)
    }

    interface Router{
        fun keDaftar()
        fun keHome(emailUser: String)
    }
}