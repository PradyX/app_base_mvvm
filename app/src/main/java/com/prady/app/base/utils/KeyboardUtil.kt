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
import android.view.View
import android.view.inputmethod.InputMethodManager

object KeyboardUtil {

    // Show keyboard for EditText
    fun showKeyboard(view: View) {
        view.requestFocus()
        view.post {
            val inputMethod =
                view.context.getSystemService(
                    Context.INPUT_METHOD_SERVICE
                ) as InputMethodManager
            inputMethod.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    // Hide keyboard for EditText
    fun hideKeyboard(view: View) {
        val inputMethod =
            view.context.getSystemService(
                Context.INPUT_METHOD_SERVICE
            ) as InputMethodManager
        inputMethod.hideSoftInputFromWindow(view.windowToken, 0)
    }
}