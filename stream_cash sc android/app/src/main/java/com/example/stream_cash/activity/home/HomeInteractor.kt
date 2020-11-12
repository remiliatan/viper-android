package com.example.stream_cash.activity.home

import android.content.Context
import android.util.Log
import android.widget.TextView
import com.example.stream_cash.api.RestApi
import com.example.stream_cash.util.Function
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeInteractor internal constructor(view: HomeContracts.View): HomeContracts.Interaksi {

    private var mContext = view as Context
    override fun inisialiasiData(namaUser: TextView, balanceUser: TextView, emailUser:String) {
        val resApi = RestApi.makeService()
        val function = Function()
        function.showProgress(mContext)
        resApi.dataUser(emailUser).enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if(response.isSuccessful){
                    val json = JSONObject(response.body()!!.string())
                    val userNama = json.getString("userNama")
                    val totalBalance = json.getString("userBalance")
                    namaUser.text = userNama
                    balanceUser.text = totalBalance
                }
                function.dismisDialog()
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("Error Failure", t.message.toString())
            }
        })
    }
}