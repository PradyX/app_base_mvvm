/*
 * *
 *  * Created by prady on 14/02/26, 2:28 pm, $today.time
 *  * Copyright (c) 2026 . All rights reserved.
 *  * Last modified 14/02/26, 2:28 pm, $file.lastModified.time
 *
 *
 */

package com.prady.app.base.ui.activities

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.button.MaterialButton
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.messaging.FirebaseMessaging
import com.prady.app.base.R
import com.prady.app.base.databinding.ActivitySplashBinding
import com.prady.app.base.factories.SharedVMF
import com.prady.app.base.helpers.Constants
import com.prady.app.base.helpers.PreferenceManager
import com.prady.app.base.models.AppVersion
import com.prady.app.base.utils.AlertDialogUtil.showAlertDialog
import com.prady.app.base.utils.getAppVersioning
import com.prady.app.base.utils.isNetworkConnected
import com.prady.app.base.utils.snackbar
import com.prady.app.base.viewModels.SharedVM
import org.kodein.di.KodeinAware
import com.prady.app.base.BuildConfig
import com.prady.app.base.ui.activities.authActivity.AuthActivity
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: SharedVMF by instance()
    private lateinit var viewModel: SharedVM
    var versionCode = ""
    var versionName = ""

    private lateinit var binding: ActivitySplashBinding
    private lateinit var preferenceManager: PreferenceManager

    companion object {
        private const val SPLASH_TIME_OUT = 1500L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this, factory)[SharedVM::class.java]
        preferenceManager = PreferenceManager.instance
//        setLanguage()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContentView(binding.root)

        versionCode = BuildConfig.VERSION_CODE.toString()
        versionName = BuildConfig.VERSION_NAME
        binding.tvAppVersion.text = "$versionName"

        val slideInRight = AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.slide_in_from_right
        )

        binding.apply {
            icon.startAnimation(slideInRight)
            progressBar.startAnimation(slideInRight)
//            tvAppName.startAnimation(slideInRight)
        }
//        checkInternet()
        setupFCM()
    }

    private fun checkInternet() {
        if (applicationContext.isNetworkConnected) {
            getAppVersioningFromFB()
        } else {
            this.showAlertDialog(
                title = "No Internet Connection !!",
                message = "Please check your internet connection.",
                posBtnText = "Try Again",
                negBtnText = null,
                showNegBtn = false,
                callback = {
                    checkInternet()
                })
        }
    }

    private fun getAppVersioningFromFB() {
        val reference = applicationContext.getAppVersioning()
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val appVersion: AppVersion? = dataSnapshot.getValue(AppVersion::class.java)
                Log.e("AppVersion ", "" + appVersion?.versionName)
                try {
                    if (appVersion?.paid == true) {
                        if (appVersion?.forceUpdate == true) {
                            if (appVersion?.versionName.equals(versionName)) {
                                setupFCM()
                            } else {
                                showAppUpdaterDialog(appVersion?.appUrl)
                            }
                        } else {
                            setupFCM()
                        }
                    } else {
                        displayUnpaidDialog(appVersion?.paidStatus)
                    }
                } catch (e: Exception) {
                    binding.root.snackbar("Error: $e")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                binding.root.snackbar("Error: $databaseError")
            }
        })
    }

    private fun setupFCM() {
        FirebaseApp.initializeApp(this)
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.e("FCMTAG", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            // Get new FCM registration token
            val token = task.result
            preferenceManager.fcmToken = token
            Log.e("FCMTAG", token + "\n pref: " + preferenceManager.fcmToken)

            /* Delaying the execution of the `setupViews()` function by 1 second. */
            Handler().postDelayed({
                setupViews()
            }, SPLASH_TIME_OUT)
        })
    }

    private fun setupViews() {
        when {
            preferenceManager.loggedIn.equals("true", ignoreCase = true) -> {
                openNextUi()
            }

            else -> {
                val i = Intent(this, AuthActivity::class.java)
                startActivity(i)
                finish()
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_left)
            }
        }
    }

    private fun openNextUi() {
        when (preferenceManager.userType) {
            Constants.userTypeCustomer -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
//            Constants.userTypeAdmin -> {
//                startActivity(Intent(this, VendorMainActivity::class.java))
//            }
            else -> {
                preferenceManager.clear()
                startActivity(Intent(this, SplashActivity::class.java))
            }
        }
        overridePendingTransition(
            R.anim.slide_in_from_right,
            R.anim.slide_out_left
        )
        finishAffinity()
    }

    private fun displayUnpaidDialog(paidStatus: String?) {
        this.showAlertDialog(
            title = "App stopped working !",
            message = "$paidStatus",
            posBtnText = "CLOSE",
            negBtnText = null,
            showNegBtn = false,
            callback = {finishAffinity()})
    }

    private fun showAppUpdaterDialog(urlSend: String?) {
        val dialog = Dialog(this)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_app_update)
        val positiveBtn: MaterialButton = dialog.findViewById(R.id.positive_btn)
        positiveBtn.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlSend)))
            dialog.dismiss()
            finish()
        }
        dialog.show()
    }
}