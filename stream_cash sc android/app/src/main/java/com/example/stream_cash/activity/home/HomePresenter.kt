package com.example.stream_cash.activity.home

import android.content.Context
import android.widget.TextView


class HomePresenter internal constructor(view: HomeContracts.View): HomeContracts.Presenter {

    private val viewRegis:HomeContracts.View = view
    private val regisInteractor:HomeInteractor = HomeInteractor(view)
    private val route:HomeRouter = HomeRouter(view as Context)



    override fun definisiData(namaUser: TextView, balanceUser: TextView, emailUser:String) {
        regisInteractor.inisialiasiData(namaUser, balanceUser, emailUser)
    }
    override fun navKeTopTrans(tipe:String) {
       route.keHalamanTopTrans(tipe)
    }
}