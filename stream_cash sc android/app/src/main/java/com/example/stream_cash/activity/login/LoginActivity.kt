package com.example.stream_cash.activity.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.example.stream_cash.R

class LoginActivity : AppCompatActivity(), LoginContracts.View {

    private var mContext = this@LoginActivity

    @BindView(R.id.edtEmail) lateinit var edtEmail:EditText
    @BindView(R.id.edtPassword) lateinit var edtPass:EditText
    @BindView(R.id.tblLogin) lateinit var tblLogin:TextView
    @BindView(R.id.keDaftar) lateinit var keDaftar:TextView

    lateinit var presenterLogin:LoginContracts.Presenter
    lateinit var routerLogin:LoginContracts.Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        presenterLogin = LoginPresenter(this)
        routerLogin = LoginRouter(this)


        tblLogin.setOnClickListener {
            val emailUser = edtEmail.text.toString().trim()
            val password = edtPass.text.toString().trim()
            presenterLogin.tombolLoginKlik(emailUser, password)
        }

        keDaftar.setOnClickListener {
            routerLogin.keDaftar()
        }
    }

    override fun onBackPressed(){
        val inte = Intent(Intent.ACTION_MAIN)
        inte.addCategory(Intent.CATEGORY_HOME)
        inte.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(inte)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
    override fun showToast(message:String) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
    }
}
