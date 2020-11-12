package com.example.stream_cash.api

import com.example.stream_cash.util.GlobalString
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestApi {

   /* fun make():Service{
        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(GlobalString.url).build()
        return retrofit.create(Service::class.java)
    }*/

    var retrofit:Retrofit? = null
    fun make(url:String):Retrofit{
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val clientOk = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

        if(retrofit == null){
            retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url).client(clientOk).build()
        }
        return retrofit!!

    }

    fun makeService():Service{
        return make(GlobalString.url).create(Service::class.java)
    }

}