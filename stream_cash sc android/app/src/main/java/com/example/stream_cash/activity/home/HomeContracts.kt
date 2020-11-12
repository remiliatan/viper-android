package com.example.stream_cash.activity.home

import android.widget.TextView

class HomeContracts {

    interface View{
        fun onDestroy()
        fun showToast(message:String)
    }

    interface Interaksi{
        fun inisialiasiData(namaUser:TextView, balanceUser:TextView, emailUser:String)
    }

    interface Presenter{
        fun definisiData(namaUser:TextView, balanceUser:TextView, emailUser:String)
        fun navKeTopTrans(tipe:String)
    }

    interface Router{
        fun keHalamanTopTrans(tipe:String)
    }
}