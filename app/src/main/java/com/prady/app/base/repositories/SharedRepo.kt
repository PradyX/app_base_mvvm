/*
 * *
 *  * Created by prady on 14/02/26, 2:28 pm, $today.time
 *  * Copyright (c) 2026 . All rights reserved.
 *  * Last modified 14/02/26, 2:28 pm, $file.lastModified.time
 *
 *
 */

package com.prady.app.base.repositories

import com.google.gson.Gson
import com.prady.app.base.data.remote.MyApi
import com.prady.app.base.data.remote.SafeApiRequest
import com.prady.app.base.models.EmptyRequest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class SharedRepo(
    private val api: MyApi
) : SafeApiRequest() {

    suspend fun apiTest(request: EmptyRequest): String {
        var response = String()
        try {
            val jsonObject = Gson().toJson(request, EmptyRequest::class.java)
            val body = jsonObject.toString()
                .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
            response = apiRequest { api.apiTest(body) }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return response
    }
}