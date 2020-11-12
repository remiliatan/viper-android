package com.example.stream_cash.activity.register


class RegisterPresenter internal constructor(view: RegisterContracts.View): RegisterContracts.Presenternya {

    private val viewRegis:RegisterContracts.View = view
    private val regisInteractor:RegisterInteractor = RegisterInteractor(view)
    override fun tombolDaftarKlik(
        emailUser: String,
        passwordUser: String,
        konfirmasiPassword: String,
        namaUser: String
    ) {
        val emailFormat = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        if(emailUser.isNotEmpty() && passwordUser.isNotEmpty() && konfirmasiPassword.isNotEmpty() && namaUser.isNotEmpty()){
            if(emailUser.matches(emailFormat.toRegex())){
                if(passwordUser == konfirmasiPassword){
                    if(namaUser.length >= 4){
                        regisInteractor.prosesDaftar(emailUser, passwordUser, namaUser)
                    }else{
                        viewRegis.showToast("Panjang nickname minimal 4 karakter")
                    }
                }else{
                    viewRegis.showToast("Konfirmasi password tidak sama")
                }
            }else{
                viewRegis.showToast("Format E-Mail salah!")
            }
        }else{
            viewRegis.showToast("Masih ada yang kosong")
        }
    }
}