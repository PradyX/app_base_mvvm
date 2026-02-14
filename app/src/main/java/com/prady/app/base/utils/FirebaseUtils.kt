/*
 * *
 *  * Created by prady on 14/02/26, 2:28 pm, $today.time
 *  * Copyright (c) 2026 . All rights reserved.
 *  * Last modified 14/02/26, 2:28 pm, $file.lastModified.time
 *
 *
 */

package com.prady.app.base.utils

import android.content.Context
import com.google.firebase.database.FirebaseDatabase
import com.prady.app.base.helpers.Constants

fun Context.getAppVersioning() = FirebaseDatabase.getInstance(Constants.FirebaseDBUrl)
    .getReference("AppList/appBase/versioning")

fun Context.getUserDetails() =
    FirebaseDatabase.getInstance(Constants.FirebaseDBUrl).getReference("AppList/appBase/users")
