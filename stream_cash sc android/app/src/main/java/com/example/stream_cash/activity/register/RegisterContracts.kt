package com.example.stream_cash.activity.register


class RegisterContracts {

    interface View{
        fun onDestroy()
        fun showToast(message:String)
    }

    interface Presenternya{
        fun tombolDaftarKlik(emailUser:String, passwordUser:String, konfirmasiPassword:String, namaUser:String)
    }

    interface Interaksi{
        fun prosesDaftar(emailUser:String, passwordUser:String, namaUser:String)
    }

    interface Router{
        fun keLogin()
    }

}