package com.example.stream_cash.activity.login


class LoginPresenter internal constructor(view: LoginContracts.View): LoginContracts.Presenter {

    private val viewRegis:LoginContracts.View = view
    private val loginInteractor:LoginInteractor = LoginInteractor(view)

    override fun tombolLoginKlik(
        emailUser: String,
        passwordUser: String
    ) {
        val emailFormat = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        if(emailUser.isNotEmpty() && passwordUser.isNotEmpty()){
            if(emailUser.matches(emailFormat.toRegex())){
                loginInteractor.prosesLogin(emailUser, passwordUser)
            }else{
                viewRegis.showToast("Format E-Mail salah!")
            }
        }else{
            viewRegis.showToast("Masih ada yang kosong")
        }
    }
}