/*
 * *
 *  * Created by prady on 14/02/26, 2:28 pm, $today.time
 *  * Copyright (c) 2026 . All rights reserved.
 *  * Last modified 14/02/26, 2:28 pm, $file.lastModified.time
 *
 *
 */

package com.prady.app.base.utils

import android.view.View

inline fun View.onDebouncedListener(
    delayInClick: Long = 5000L,
    crossinline listener: (View) -> Unit
) {
    val enableAgain = Runnable { isEnabled = true }

    setOnClickListener {
        if (isEnabled) {
            isEnabled = false
            postDelayed(enableAgain, delayInClick)
            listener(it)
        }
    }
}