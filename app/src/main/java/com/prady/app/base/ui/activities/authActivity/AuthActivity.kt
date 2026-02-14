/*
 * *
 *  * Created by prady on 14/02/26, 2:28 pm, $today.time
 *  * Copyright (c) 2026 . All rights reserved.
 *  * Last modified 14/02/26, 2:28 pm, $file.lastModified.time
 *
 *
 */

package com.prady.app.base.ui.activities.authActivity

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.prady.app.base.R
import com.prady.app.base.databinding.ActivityAuthBinding
import com.prady.app.base.factories.AuthVMF
import com.prady.app.base.helpers.PreferenceManager
import com.prady.app.base.utils.setEdgeToEdgeInset
import com.prady.app.base.viewModels.AuthVM
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.util.*

class AuthActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: AuthVMF by instance()
    private lateinit var viewModel: AuthVM

    private lateinit var preferenceManager: PreferenceManager
    private lateinit var binding: ActivityAuthBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, factory)[AuthVM::class.java]
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferenceManager = PreferenceManager.instance
        setEdgeToEdgeInset(binding.root)
        setupNavController()
    }

    private fun setupNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
    }


    fun setLanguage(language: String) {
        preferenceManager.languagePrefs = language
        val local = Locale(language)
        Locale.setDefault(local)
        val configuration = Configuration()
        configuration.setLocale(local)
        resources.updateConfiguration(configuration, baseContext.resources.displayMetrics)
        this.recreate()
    }
}