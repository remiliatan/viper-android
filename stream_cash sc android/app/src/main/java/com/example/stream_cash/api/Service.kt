package com.example.stream_cash.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Service {

    @FormUrlEncoded
    @POST("daftar")
    fun prosesDaftar(@Field("emailUser") emailUser:String,
                     @Field("passwordUser") passwordUser:String,
                     @Field("namaUser") namaUser:String) : Call<ResponseBody>

    @FormUrlEncoded
    @POST("login")
    fun prosesLogin(@Field("emailUser")emailUser: String,
                    @Field("passwordUser") passwordUser: String): Call<ResponseBody>

    @FormUrlEncoded
    @POST("data")
    fun dataUser(@Field("emailUser")emailUser: String): Call<ResponseBody>

    @FormUrlEncoded
    @POST("topup")
    fun topUpProses(@Field("nominalTopUp")nominal:String, @Field("targetEmail") targetEmail:String,
                    @Field("apiKey") apiKey:String): Call<ResponseBody>

    @FormUrlEncoded
    @POST("transfer")
    fun transferProses(@Field("nominalTopUp")nominal:String, @Field("targetEmail") targetEmail:String,
                    @Field("apiKey") apiKey:String, @Field("pengirimEmail")pengirimEmail:String): Call<ResponseBody>

    @FormUrlEncoded
    @POST("withdraw")
    fun withdrawProses(@Field("nominalWithdraw")nominal:String, @Field("targetEmail") targetEmail:String,
                       @Field("apiKey") apiKey:String): Call<ResponseBody>



}