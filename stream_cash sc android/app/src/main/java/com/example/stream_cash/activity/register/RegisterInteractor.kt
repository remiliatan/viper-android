package com.example.stream_cash.activity.register

import android.content.Context
import android.util.Log
import com.example.stream_cash.api.RestApi
import com.example.stream_cash.util.Function
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterInteractor internal constructor(view: RegisterContracts.View) : RegisterContracts.Interaksi {

    val mContext = view as Context
    val viewRegis:RegisterContracts.View  = view
    private var router: RegisterContracts.Router = RegisterRouter(view as Context)

    override fun prosesDaftar(
        emailUser: String,
        passwordUser: String,
        namaUser: String
    ) {
        val retrofitService = RestApi.makeService()
        val function = Function()
        function.showProgress(mContext)
        retrofitService.prosesDaftar(emailUser, passwordUser, namaUser).enqueue(object :
            Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if(response.isSuccessful){
                    //daftar berhasil, save shared
                    val json = JSONObject(response.body()!!.string())
                    val success = json.getInt("success")
                    if(success == 1){
                        viewRegis.showToast(json.getString("message"))
                        router.keLogin()
                    }else{
                        viewRegis.showToast(json.getString("message"))
                    }
                    function.dismisDialog()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("Error Failure", t.message.toString())
            }
        })
    }

}