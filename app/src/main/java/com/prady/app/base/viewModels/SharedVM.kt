/*
 * *
 *  * Created by prady on 14/02/26, 2:28 pm, $today.time
 *  * Copyright (c) 2026 . All rights reserved.
 *  * Last modified 14/02/26, 2:28 pm, $file.lastModified.time
 *
 *
 */

package com.prady.app.base.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prady.app.base.helpers.Coroutines
import com.prady.app.base.models.EmptyRequest
import com.prady.app.base.repositories.SharedRepo

class SharedVM(private val repository: SharedRepo) : ViewModel() {

    var latitude: MutableLiveData<Double> = MutableLiveData()
    var logitude: MutableLiveData<Double> = MutableLiveData()
    var fullAddress: MutableLiveData<String> = MutableLiveData()
    val error: MutableLiveData<Error> = MutableLiveData()

    init {
        error.value = Error()
    }

    fun setLatitude(latitude: Double) {
        this.latitude.postValue(latitude)
    }

    fun setLongitude(longitude: Double) {
        logitude.postValue(longitude)
    }

    fun setFullAddress(fullAddress: String) {
        this.fullAddress.postValue(fullAddress)
    }

    data class Error(var error: String? = null)

    // Rest APIS
    var apiTest = MutableLiveData<String>()
    fun getApiTest(request: EmptyRequest) {
        Coroutines.main {
            apiTest.postValue(repository.apiTest(request))
        }
    }
}