package com.example.stream_cash.activity.topuptrans

import android.content.Context
import com.example.stream_cash.util.GlobalString


class TopUpTransPresenter internal constructor(view: TopUpTransContracts.View): TopUpTransContracts.Presenter {

    private val viewRegis:TopUpTransContracts.View = view
    private val regisInteractor:TopUpTransInteractor = TopUpTransInteractor(view)

    private var context:Context = view as Context

    override fun tombolTransferClick(nominalTra: String, targetEmail: String, emailPengirim:String) {

        val emailFormat = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        val shared = context.getSharedPreferences(GlobalString.shared, Context.MODE_PRIVATE)
        val emailLogin = shared.getString("emailUser", "")
        if(targetEmail.isNotEmpty()){
            if(targetEmail.matches(emailFormat.toRegex())){
                if(nominalTra.isNotEmpty()){
                    if(emailLogin != targetEmail){
                        regisInteractor.prosesTransferClick(nominalTra, targetEmail, emailLogin!!)
                    }else{
                        viewRegis.showToast("Tidak bisa transfer ke diri sendiri")
                    }
                }else{
                    viewRegis.showToast("Nominal harus lebih dari 0")
                }
            }else{
                viewRegis.showToast("Format E-Mail salah!")
            }
        }else{
            viewRegis.showToast("Masih ada yang kosong")
        }

    }

    override fun tombolTopUpClick(nominalTra: String, targetEmail: String) {
        val emailFormat = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        if(targetEmail.isNotEmpty()){
            if(targetEmail.matches(emailFormat.toRegex())){
                if(nominalTra.isNotEmpty()){
                        regisInteractor.prosesTopUpClick(nominalTra, targetEmail)
                }else{
                    viewRegis.showToast("Nominal harus lebih dari 0")
                }
            }else{
                viewRegis.showToast("Format E-Mail salah!")
            }
        }else{
            viewRegis.showToast("Masih ada yang kosong")
        }
    }

    override fun withdrawClick(nominalTra: String, targetEmail: String) {
        val emailFormat = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        if(targetEmail.isNotEmpty()){
            if(targetEmail.matches(emailFormat.toRegex())){
                if(nominalTra.isNotEmpty()){
                    regisInteractor.prosesWithdraw(nominalTra, targetEmail)
                }else{
                    viewRegis.showToast("Nominal harus lebih dari 0")
                }
            }else{
                viewRegis.showToast("Format E-Mail salah!")
            }
        }else{
            viewRegis.showToast("Masih ada yang kosong")
        }
    }


}