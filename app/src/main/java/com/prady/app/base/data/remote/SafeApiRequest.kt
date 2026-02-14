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
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

/* It's a class that makes an API call and returns the response body if the call is successful,
otherwise it throws an exception */
abstract class SafeApiRequest {
    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val error = response.errorBody()?.string()

            val message = StringBuilder()
            message.append("Error Code: ${response.code()}\n")

            error?.let {
                try {
                    message.append(JSONObject(it))
                } catch (e: JSONException) {
                    message.append(e)
                }
            }
            Constants.apiErrors = message.toString()
            throw ApiException(message.toString())
        }
    }
}