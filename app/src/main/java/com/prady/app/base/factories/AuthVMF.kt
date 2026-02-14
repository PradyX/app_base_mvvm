/*
 * *
 *  * Created by prady on 14/02/26, 2:28 pm, $today.time
 *  * Copyright (c) 2026 . All rights reserved.
 *  * Last modified 14/02/26, 2:28 pm, $file.lastModified.time
 *
 *
 */

package com.prady.app.base.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prady.app.base.repositories.AuthRepo
import com.prady.app.base.viewModels.AuthVM

@Suppress("UNCHECKED_CAST")

class AuthVMF(
    private val repository: AuthRepo
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthVM(repository) as T
    }
}