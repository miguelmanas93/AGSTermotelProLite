package com.miguel.ags.agstermotelprolite.network

import android.content.Context
import com.miguel.ags.agstermotelprolite.utils.Constantes
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class RetrofitClient {

    private val apiService: APIService

    init{
        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client: OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()

        val moshi = Moshi.Builder().build()

        var retrofitBuilder =Retrofit.Builder()
            .baseUrl(Constantes.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))

        val retrofit = retrofitBuilder.client(client).build()

        apiService = retrofit.create(APIService::class.java)
    }

    fun getInstance(): APIService = apiService
}
