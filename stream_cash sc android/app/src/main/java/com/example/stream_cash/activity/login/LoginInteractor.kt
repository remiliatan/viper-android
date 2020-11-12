package com.example.stream_cash.activity.login

import android.content.Context
import android.util.Log
import com.example.stream_cash.api.RestApi
import com.example.stream_cash.util.Function
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginInteractor internal constructor(view: LoginContracts.View) : LoginContracts.Interaksi {

    val mContext = view as Context
    val viewRegis:LoginContracts.View  = view
    private var router: LoginContracts.Router = LoginRouter(view as Context)

    override fun prosesLogin(
        emailUser: String,
        passwordUser: String
    ) {
        val retrofitService = RestApi.makeService()
        val function = Function()
        function.showProgress(mContext)
        
        retrofitService.prosesLogin(emailUser, passwordUser).enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if(response.isSuccessful){
                    val json = JSONObject(response.body()!!.string())
                    val success = json.getInt("success")
                    val message = json.getString("message")
                    //login berhasil
                    if(success == 1){
                        viewRegis.showToast(message)
                        router.keHome(emailUser)
                    }else{
                        viewRegis.showToast(message)
                    }
                    function.dismisDialog()
                }else{
                    viewRegis.showToast("Terjadi kesalahan")
                }
                
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("Error Failure", t.message.toString())
            }
        })
    }

}