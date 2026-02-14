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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.prady.app.base.R
import com.prady.app.base.databinding.FragmentOtpVerifyBinding
import com.prady.app.base.factories.AuthVMF
import com.prady.app.base.helpers.Constants
import com.prady.app.base.helpers.PreferenceManager
import com.prady.app.base.ui.activities.MainActivity
import com.prady.app.base.utils.showKeyboard
import com.prady.app.base.utils.toast
import com.prady.app.base.viewModels.AuthVM

import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import org.kodein.di.generic.kcontext

class OtpVerifyFragment : Fragment(), KodeinAware {
    override val kodeinContext = kcontext<Fragment>(this)
    override val kodein: Kodein by kodein()
    private val factory: AuthVMF by instance()
    private lateinit var viewModel: AuthVM

    private lateinit var binding: FragmentOtpVerifyBinding
    private lateinit var preferenceManager: PreferenceManager
    private var otp = ""
    private var number = ""
    private var userType = ""
    private var otpType = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOtpVerifyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferenceManager = PreferenceManager.instance
        viewModel = ViewModelProvider(requireActivity(), factory)[AuthVM::class.java]

        otp = arguments?.getString("otp").toString()
        number = arguments?.getString("number").toString()
        userType = arguments?.getString("userType").toString()
        otpType = arguments?.getString("otpType").toString()
        setupViews()
        setupListeners()
        setupCustomMargins()
    }

    private fun setupViews() {
//        binding.pinView.setText(otp)
        binding.pinView.requestFocus()
        context?.showKeyboard(binding.root)
        binding.etNumber.text = "+91-$number"
    }

    private fun setupListeners() {
        binding.btnBack.setOnClickListener {
//            val action = OtpVerifyFragmentDirections.actionOtpVerifyFragmentToSigninFragment()
//            findNavController().navigate(action)
        }

        binding.btnSignIn.setOnClickListener {
            Log.e("userType ", "" + userType)
//            when (otp) {
//                binding.pinView.text.toString() -> {
                    preferenceManager.phone = number
                    preferenceManager.loggedIn = "true"
                    openNextUi()
//                    context?.toast("Coming Soon !")
//                }
//                else -> {
//                    binding.pinView.setText("")
//                    context?.hideKeyboard(binding.root)
//                    (activity as AuthActivity).apiErrorDialog("You have entered wrong OTP. Please try again !")
//                }
//            }
        }
    }

    private fun openNextUi() {
        if (otpType.equals("forgot", ignoreCase = true)) {
            val action = OtpVerifyFragmentDirections.actionOtpVerifyFragmentToSignInFragment()
            findNavController().navigate(action)
        } else {
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
//                Constants.userTypeDepartment -> {
//                    preferenceManager.userType = Constants.userTypeDepartment
//                    activity?.startActivity(Intent(activity, VendorMainActivity::class.java))
//                    activity?.overridePendingTransition(
//                        R.anim.slide_in_from_right,
//                        R.anim.slide_out_left
//                    )
//                    activity?.finishAffinity()
//                }
                else -> context?.toast("Unable to get user type !")
            }
        }
    }

//    private fun saveUserProfile(request: UserRegisterReq) {
//        viewModel.saveUserProfile = MutableLiveData()
//        viewModel.saveUserProfile.observe(requireActivity()) {
//            val response = Gson().fromJson(it, EmptyResponse::class.java)
//            Log.e("saveUserProfile ", "$response")
//            if (response.status) {
//                val intent = Intent(activity, ProfileCreationActivity::class.java)
//                activity?.startActivity(intent)
//                activity?.overridePendingTransition(
//                    R.anim.slide_in_from_right,
//                    R.anim.slide_out_left
//                )
//                activity?.finishAffinity()
//            } else {
//                binding.root.snackbar("Error: ${response.message}")
//            }
//        }
//        viewModel.saveUserProfile(request)
//    }

    private fun showDialog() {
//        CFAlertDialog.Builder(context)
//            .setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT)
//            .setHeaderView(R.layout.dialog_header_successfull)
//            .setTitle("Account recovery successful !!")
//            .setMessage("Please sign in using this credential to continue.")
//            .addButton(
//                "SIGN IN", -1, -1,
//                CFAlertDialog.CFAlertActionStyle.POSITIVE,
//                CFAlertDialog.CFAlertActionAlignment.JUSTIFIED
//            ) { dialog: DialogInterface, _: Int ->
////                val intent = Intent (activity, SignInActivity::class.java)
////                activity?.startActivity(intent)
//                dialog.dismiss()
//            }
//            .setCancelable(false)
//            .show()
    }

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