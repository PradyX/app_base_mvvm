/*
 * *
 *  * Created by prady on 14/02/26, 2:28 pm, $today.time
 *  * Copyright (c) 2026 . All rights reserved.
 *  * Last modified 14/02/26, 2:28 pm, $file.lastModified.time
 *
 *
 */

package com.prady.app.base.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.prady.app.base.databinding.ActivityMainBinding
import com.prady.app.base.factories.SharedVMF
import com.prady.app.base.helpers.PreferenceManager
import com.prady.app.base.models.EmptyRequest
import com.prady.app.base.models.EmptyResponse
import com.prady.app.base.utils.setEdgeToEdgeInset
import com.prady.app.base.viewModels.SharedVM
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory: SharedVMF by instance()
    private lateinit var viewModel: SharedVM
    private lateinit var preferenceManager: PreferenceManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferenceManager = PreferenceManager.instance
        viewModel = ViewModelProvider(this, factory)[SharedVM::class.java]
        setEdgeToEdgeInset(binding.root)

        preferenceManager.userType = "12312"

        println("prefTest : " + preferenceManager.userType)

        apiTest(EmptyRequest())
    }

    private fun apiTest(request: EmptyRequest) {
        viewModel.apiTest = MutableLiveData()
        viewModel.apiTest.observe(this) {
            val response = Gson().fromJson(it, EmptyResponse::class.java)
            Log.e("savefcm ", "$response")
            if (response != null) {
                if (response.status) {
                Log.e("savefcm ", "${response.message}")
                } else {
//                    apiErrorDialog(response.message)
                }
            } else {
//                apiErrorDialog(Constants.apiErrors)
            }
        }
        viewModel.getApiTest(request)
    }
}