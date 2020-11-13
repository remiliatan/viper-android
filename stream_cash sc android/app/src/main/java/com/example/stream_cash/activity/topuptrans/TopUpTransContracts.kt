package com.example.stream_cash.activity.topuptrans


class TopUpTransContracts {

    interface View{
        fun onDestroy()
        fun resetEditText()
        fun showToast(message:String)
    }

    interface Interaksi{
        //transfer bisa juga digunakan untuk topup
        fun prosesTopUpClick(nominalTra: String, targetEmail: String)
        fun prosesTransferClick(nominalTra: String, targetEmail: String, pengirimEmail:String)
        fun prosesWithdraw(nominalTra: String, targetEmail: String)
    }

    interface Presenter{
        fun tombolTransferClick(nominalTra:String, targetEmail:String, pengirimEmail:String)
        fun tombolTopUpClick(nominalTra:String, targetEmail:String)
        fun withdrawClick(nominalTra:String, targetEmail:String)
    }

    interface Router{
        //nothing
    }
}