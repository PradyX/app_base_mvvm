/*
 * *
 *  * Created by prady on 14/02/26, 2:28 pm, $today.time
 *  * Copyright (c) 2026 . All rights reserved.
 *  * Last modified 14/02/26, 2:28 pm, $file.lastModified.time
 *
 *
 */

package com.prady.app.base.ui.activities.authActivity.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.prady.app.base.R
import com.prady.app.base.databinding.FragmentSigninBinding
import com.prady.app.base.factories.AuthVMF
import com.prady.app.base.helpers.Constants
import com.prady.app.base.helpers.PreferenceManager
import com.prady.app.base.utils.toast
import com.prady.app.base.viewModels.AuthVM
import com.prady.app.base.ui.activities.MainActivity
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import org.kodein.di.generic.kcontext

class SignInFragment : Fragment(), KodeinAware {
    private lateinit var binding: FragmentSigninBinding
    override val kodeinContext = kcontext<Fragment>(this)
    override val kodein: Kodein by kodein()
    private val factory: AuthVMF by instance()
    private lateinit var viewModel: AuthVM
    private lateinit var preferenceManager: PreferenceManager
    private var sessionNumber = ""
    private val sessionNames: MutableList<String> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSigninBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        preferenceManager = PreferenceManager.instance
        viewModel = ViewModelProvider(requireActivity(), factory)[AuthVM::class.java]
        setupViews()
        setupListeners()
    }

    private fun setupViews() {
//        setupCustomMargins()

        binding.apply {
        }
    }

    private fun setupListeners() {
        binding.apply {
            btnBack.setOnClickListener {
                activity?.finishAffinity()
            }
            btnSignUp.setOnClickListener {
                val action = SignInFragmentDirections.actionSignInFragmentToSignUpFragment()
                findNavController().navigate(action)
            }
            btnSignIn.setOnClickListener {
                when {
                    etNumber.text.toString()
                        .isEmpty() || etNumber.text.toString().length < 10 -> etNumber.error =
                        "Please enter your phone number"
                    etPass.text.toString().isEmpty() -> etPass.error = "Please enter your password"
                    else -> {
                        viewModel.setUserType(Constants.userTypeCustomer) //
                        btnSignIn.isEnabled = false
//                        hitApi(
//                            SigninReq(
//                                "" + etPassword.text,
//                                "" + sessionNumber,
//                                "" + etUsername.text
//                            )
//                        )
                        preferenceManager.userType = Constants.userTypeCustomer
                        activity?.startActivity(Intent(activity, MainActivity::class.java))
                        activity?.overridePendingTransition(
                            R.anim.slide_in_from_right,
                            R.anim.slide_out_left
                        )
                        activity?.finishAffinity()
                    }
                }
            }
        }
    }

    private fun openNextUI() {
        when (viewModel.userType.value) {
            Constants.userTypeCustomer -> {
                preferenceManager.userType = Constants.userTypeCustomer
                activity?.startActivity(Intent(activity, MainActivity::class.java))
                activity?.overridePendingTransition(
                    R.anim.slide_in_from_right,
                    R.anim.slide_out_left
                )
                activity?.finishAffinity()
            }
//            Constants.userTypeDepartment -> {
//                preferenceManager.userType = Constants.userTypeAdmin
//                activity?.startActivity(Intent(activity, VendorMainActivity::class.java))
//                activity?.overridePendingTransition(
//                    R.anim.slide_in_from_right,
//                    R.anim.slide_out_left
//                )
//                activity?.finishAffinity()
//            }
            else -> context?.toast("Unable to get user type !")
        }
    }

//    private fun hitApi(loginReq: SignInReq) {
//        LoadingUtils.showDialog(requireContext(), false)
//        viewModel.signIn = MutableLiveData()
//        viewModel.signIn.observe(requireActivity()) {
//            val response = Gson().fromJson(it, SignInRes::class.java)
//            Log.e("response", "$response")
//            if (response != null) {
//                if (response.status) {
//                    LoadingUtils.hideDialog()
//                    binding.btnSignIn.isEnabled = false
////                context?.copyToClipboard(response.response?.otp.toString())
////                context?.toast("copied to clipboard")
//                    preferenceManager.userid = response.response.userID
//                    openNextUI()
//                } else {
//                    LoadingUtils.hideDialog()
//                    (activity as AuthActivity).apiErrorDialog("Sorry, your credentials were invalid !")
////                    binding.progressBar.visibility = View.GONE
//                    binding.btnSignIn.isEnabled = true
//                }
//            } else {
//                LoadingUtils.hideDialog()
//                binding.btnSignIn.isEnabled = true
//                (activity as AuthActivity).apiErrorDialog(Constants.apiErrors)
//            }
//        }
//        viewModel.signIn(loginReq)
//    }

    private fun setupCustomMargins() {
        // status bar height
        var statusBarHeight = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            statusBarHeight = resources.getDimensionPixelSize(resourceId)
        }

        // navigation bar height
        var navigationBarHeight = 0
        val resourceId2 = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        if (resourceId > 0) {
            navigationBarHeight = resources.getDimensionPixelSize(resourceId2)
        }

        val param = binding.llContainer.layoutParams as ViewGroup.MarginLayoutParams
        param.setMargins(0, statusBarHeight, 0, navigationBarHeight)
        binding.llContainer.layoutParams = param
    }
}