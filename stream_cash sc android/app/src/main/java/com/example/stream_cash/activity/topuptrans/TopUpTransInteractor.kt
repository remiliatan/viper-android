package com.example.stream_cash.activity.topuptrans

import android.content.Context
import android.util.Log
import com.example.stream_cash.api.RestApi
import com.example.stream_cash.util.Function
import com.example.stream_cash.util.GlobalString
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopUpTransInteractor internal constructor(view: TopUpTransContracts.View) : TopUpTransContracts.Interaksi {

    val mContext = view as Context
    val viewRegis:TopUpTransContracts.View  = view
    override fun prosesTopUpClick(nominalTra: String, targetEmail: String) {
        val apiRest = RestApi.makeService()
        val function = Function()
        function.showProgress(mContext)

        apiRest.topUpProses(nominalTra, targetEmail, GlobalString.keyApi)
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val json = JSONObject(response.body()!!.string())
                        val message = json.getString("message")
                        val success = json.getInt("success")
                        if (success != 0) {
                            viewRegis.resetEditText()
                        }
                        viewRegis.showToast(message)
                        function.dismisDialog()
                    } else {
                        viewRegis.showToast("Ada kesalahan")
                        function.dismisDialog()
                    }

                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("Error Failure", t.message.toString())
                }
            })
    }

    override fun prosesTransferClick(nominalTra: String, targetEmail: String, pengirimEmail:String) {
        val apiRest = RestApi.makeService()
        val function = Function()
        function.showProgress(mContext)

        apiRest.transferProses(nominalTra, targetEmail, GlobalString.keyApi, pengirimEmail)
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val json = JSONObject(response.body()!!.string())
                        val message = json.getString("message")
                        val success = json.getInt("success")
                        if (success != 0) {
                            viewRegis.resetEditText()
                        }
                        viewRegis.showToast(message)
                        function.dismisDialog()
                    } else {
                        viewRegis.showToast("Ada kesalahan")
                        function.dismisDialog()
                    }

                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("Error Failure", t.message.toString())
                }
            })

    }
}