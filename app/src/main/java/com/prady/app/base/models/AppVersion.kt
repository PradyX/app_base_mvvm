/*
 * *
 *  * Created by prady on 14/02/26, 2:28 pm, $today.time
 *  * Copyright (c) 2026 . All rights reserved.
 *  * Last modified 14/02/26, 2:28 pm, $file.lastModified.time
 *
 *
 */

package com.prady.app.base.models

data class AppVersion(
    val forceUpdate: Boolean = false,
    val paid: Boolean = false,
    val paidStatus: String = "",
    val versionCode: Int = 0,
    val versionName: String = "",
    val appUrl: String = ""
)