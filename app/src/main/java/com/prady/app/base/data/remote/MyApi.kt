/*
 * *
 *  * Created by prady on 14/02/26, 2:28 pm, $today.time
 *  * Copyright (c) 2026 . All rights reserved.
 *  * Last modified 14/02/26, 2:28 pm, $file.lastModified.time
 *
 *
 */

package com.prady.app.base.data.remote

import com.prady.app.base.helpers.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface MyApi {

    @POST("GetBankCodelist")
    suspend fun apiTest(
        @Body parameter: RequestBody
    ): Response<String>


    /* Creating a singleton object of MyApi. */
    companion object {
        /**
         * It creates a Retrofit instance.
         *
         * @param networkConnectionInterceptors This is the interceptor that we created in the previous
         * step.
         * @return Retrofit.Builder()
         */
        operator fun invoke(
            networkConnectionInterceptors: NetworkConnectionInterceptors
        ): MyApi {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val headerInterceptor = CustomInterceptor()

            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptors)
                .addInterceptor(headerInterceptor)
                .addInterceptor(interceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl(Constants.BaseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }

    /* > This class is an interceptor that intercepts the request and adds a custom header to it */
    class CustomInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            val request = chain.request().newBuilder()
                .build()
//            val request = chain.request().newBuilder().addHeader(
//                "Authorization",
//                "Bearer " + instance.token
//            )
            return chain.proceed(request)
        }
    }
}