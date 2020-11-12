package com.example.stream_cash.activity.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.example.stream_cash.R

class RegisterActivity : AppCompatActivity(), RegisterContracts.View {

    @BindView(R.id.edtEmail) lateinit var edtEmail:EditText
    @BindView(R.id.edtPassword) lateinit var edtPassword:EditText
    @BindView(R.id.edtKonfirmasi) lateinit var edtKonfirmasi:EditText
    @BindView(R.id.edtNickname) lateinit var edtNickname:EditText
    @BindView(R.id.btnRegister) lateinit var btnRegister:TextView
    @BindView(R.id.keLogin) lateinit var keLogin:TextView

    private var mContext = this@RegisterActivity
    //implement presenter
    lateinit var presenterDaftar:RegisterContracts.Presenternya
    lateinit var routerDaftar:RegisterContracts.Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        ButterKnife.bind(this)

        presenterDaftar = RegisterPresenter(this)
        routerDaftar = RegisterRouter(this)

        btnRegister.setOnClickListener {
            val emailUser = edtEmail.text.toString().trim()
            val password = edtPassword.text.toString().trim()
            val konfirmasi = edtKonfirmasi.text.toString().trim()
            val nickname = edtNickname.text.toString().trim()
            presenterDaftar.tombolDaftarKlik(emailUser, password, konfirmasi, nickname)
        }
        keLogin.setOnClickListener {
            routerDaftar.keLogin()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun showToast(message:String) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
    }

}
