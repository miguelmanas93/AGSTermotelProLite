package com.miguel.ags.agstermotelprolite.network

import com.miguel.ags.agstermotelprolite.utils.Constantes
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

        /**
         * Companion object to create the Mercatus
         */
        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(Constantes.BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        }
    }

}