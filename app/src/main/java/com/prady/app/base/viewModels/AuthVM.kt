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
import com.prady.app.base.repositories.AuthRepo

class AuthVM(private val repository: AuthRepo) : ViewModel() {

    var userType: MutableLiveData<String> = MutableLiveData()

    fun setUserType(type: String) {
        userType.value = type
    }

//    var signIn = MutableLiveData<String>()
//    fun signIn(request: SigninReq) {
//        Coroutines.main {
//            signIn.postValue(repository.signIn(request))
//        }
//    }
}