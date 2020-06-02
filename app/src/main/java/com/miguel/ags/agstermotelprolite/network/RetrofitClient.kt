package com.miguel.ags.agstermotelprolite.network

import com.miguel.ags.agstermotelprolite.utils.Constantes
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class RetrofitClient {

    companion object {
        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client: OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()


        fun getRetrofitInstance(): Retrofit {
            val moshi = Moshi.Builder().build()
                return Retrofit.Builder()
                .baseUrl(Constantes.BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        }
    }

}