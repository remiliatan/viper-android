package com.example.stream_cash.activity.topuptrans

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.example.stream_cash.R
import com.example.stream_cash.util.GlobalString

class TopUpTransferActivity : AppCompatActivity(), TopUpTransContracts.View {

    @BindView(R.id.backButton) lateinit var back:TextView
    @BindView(R.id.parentTopup) lateinit var parentTopUp:LinearLayout
    @BindView(R.id.parentTransfer) lateinit var parentTransfer:LinearLayout
    @BindView(R.id.nominalTransfer) lateinit var nominalTransfer:EditText
    @BindView(R.id.topUpNominal) lateinit var topUpNominal:EditText
    @BindView(R.id.targetEmail) lateinit var targetEmail:EditText
    @BindView(R.id.tblTransfer) lateinit var tblTransfer:TextView
    @BindView(R.id.tblTopup) lateinit var tblTopup:TextView
    @BindView(R.id.textIndicator) lateinit var textIndicator:TextView

    private var mContext = this@TopUpTransferActivity
    private lateinit var presenter:TopUpTransPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_up_transfer)
        ButterKnife.bind(mContext)
        back.setOnClickListener { onBackPressed() }
        presenter = TopUpTransPresenter(mContext)

        //inisialiasi tipe halaman yang akan dilakukan
        val inte = intent
        val tipe = inte.getStringExtra("tipe")
        textIndicator.text = tipe
        tblTopup.text = tipe
        if(tipe == "Top Up" || tipe == "Withdraw"){
            parentTransfer.visibility = View.GONE
        }else if(tipe == "Transfer"){
            parentTopUp.visibility = View.GONE
        }

        tblTopup.setOnClickListener {
            val nominal = topUpNominal.text.toString()
            //ambil email login jika topup
            val shared = getSharedPreferences(GlobalString.shared, Context.MODE_PRIVATE)
            val emailTarget = shared.getString("emailUser", "")

            if(tipe == "Top Up"){
                presenter.tombolTopUpClick(nominal, emailTarget!!)
            }else{
                presenter.withdrawClick(nominal, emailTarget!!)
            }

        }
        tblTransfer.setOnClickListener {
            val nominal = nominalTransfer.text.toString()
            val emailTarget = targetEmail.text.toString().trim()
            presenter.tombolTransferClick(nominal, emailTarget, "")
        }
    }

    override fun resetEditText() {
        nominalTransfer.text = null
        topUpNominal.text = null
        targetEmail.text = null
    }

    override fun showToast(message: String) {
       Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
