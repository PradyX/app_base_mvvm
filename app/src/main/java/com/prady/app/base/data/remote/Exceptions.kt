/*
 * *
 *  * Created by prady on 14/02/26, 2:28 pm, $today.time
 *  * Copyright (c) 2026 . All rights reserved.
 *  * Last modified 14/02/26, 2:28 pm, $file.lastModified.time
 *
 *
 */

package com.prady.app.base.data.remote

import java.io.IOException

/* It's a Kotlin class that extends the IOException class and takes a message as a parameter */
class ApiException(message: String) : IOException(message)

/* NoInternetException is a class that extends IOException and takes a String as a parameter. */
class NoInternetException(message: String) : IOException(message)