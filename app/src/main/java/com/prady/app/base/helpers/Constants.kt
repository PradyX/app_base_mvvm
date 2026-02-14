/*
 * *
 *  * Created by prady on 14/02/26, 2:28 pm, $today.time
 *  * Copyright (c) 2026 . All rights reserved.
 *  * Last modified 14/02/26, 2:28 pm, $file.lastModified.time
 *
 *
 */

package com.prady.app.base.helpers

import com.prady.app.base.BuildConfig

object Constants {
    // base url
    const val BaseUrl = BuildConfig.API_URL
    const val FirebaseDBUrl = BuildConfig.FIREBASE_URL
    const val imgBaseUrl = BuildConfig.IMG_BASE_URL

    var apiErrors = ""

    const val userTypeCustomer = "customer"
    const val userTypeDepartment = "department"
    const val userTypeAdmin = "admin"

    var searchedLat = 0.0
    var searchedLog = 0.0

    // dummy arrays
    val genderArray = arrayListOf("Male", "Female", "Other")

    const val dummyQrCode =
        "https://firebasestorage.googleapis.com/v0/b/app-otp-xxxx.appspot.com/o/qrcode.png?alt=media&token=c4672ab7-29c9-4069-8f14-bf22d40a8651"
    const val dummyImg =
        "https://firebasestorage.googleapis.com/v0/b/app-otp-xxxx.appspot.com/o/dummy_user.png?alt=media&token=773d79d3-bb00-4e07-b0a0-9e3d0963ddcc"

    val stateArray = arrayListOf(
        "Andhra Pradesh Telangana",
        "Assam",
        "Bihar Jharkhand",
        "Chennai",
        "Delhi NCR",
        "Gujarat",
        "Haryana",
        "Himachal Pradesh",
        "Jammu Kashmir",
        "Karnataka",
        "Kerala",
        "Kolkata",
        "Madhya Pradesh Chhattisgarh",
        "Maharashtra Goa",
        "Mumbai",
        "North East",
        "Orissa",
        "Punjab",
        "Rajasthan",
        "Tamil Nadu",
        "UP East",
        "UP West",
        "West Bengal"
    )

//    val itemsGridSmall = listOf(
//        ItemsMD(R.drawable.s1, "Men's Shoes", "Example 1", "2599", ""),
//        ItemsMD(R.drawable.s2, "Men's Shoes", "Example 2", "3399", ""),
//        ItemsMD(R.drawable.s3, "Women's Shoes", "Example 3", "2299", ""),
//        ItemsMD(R.drawable.s4, "Men's Shoes", "Example 4", "6999", ""))
}