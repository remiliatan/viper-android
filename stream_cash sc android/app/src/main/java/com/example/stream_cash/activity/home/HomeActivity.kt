package com.example.stream_cash.activity.home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.example.stream_cash.R
import com.example.stream_cash.activity.login.LoginActivity
import com.example.stream_cash.util.GlobalString

class HomeActivity : AppCompatActivity(), HomeContracts.View {

    @BindView(R.id.balanceUser) lateinit var balanceUser:TextView
    @BindView(R.id.tblTransfer) lateinit var tblTransfer:TextView
    @BindView(R.id.topUpBtn) lateinit var topupBtn:TextView
    @BindView(R.id.wdBtn) lateinit var wdBtn:TextView
    @BindView(R.id.namaUser) lateinit var namaUser:TextView
    @BindView(R.id.keluar) lateinit var keluar:TextView

    private var emailUser:String? = null
    var mContext = this@HomeActivity
    lateinit var presenter: HomePresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        ButterKnife.bind(mContext)
        presenter = HomePresenter(mContext)

        //ambil data dari shared
        val shared = getSharedPreferences(GlobalString.shared, Context.MODE_PRIVATE)
        emailUser = shared.getString("emailUser", "")
        Log.e("emailUser", emailUser!!)
        presenter.definisiData(namaUser, balanceUser, emailUser!!)
        topupBtn.setOnClickListener {
            presenter.navKeTopTrans("Top Up")
        }
        tblTransfer.setOnClickListener {
            presenter.navKeTopTrans("Transfer")
        }
        wdBtn.setOnClickListener {
            presenter.navKeTopTrans("Withdraw")
        }

        keluar.setOnClickListener {
            val editor = shared.edit()
            editor.putString(emailUser, null)
            editor.apply()
            startActivity(Intent(mContext, LoginActivity::class.java))
            showToast("Berhasil keluar")
        }
    }

    override fun showToast(message: String) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        presenter.definisiData(namaUser, balanceUser, emailUser!!)
    }
}
