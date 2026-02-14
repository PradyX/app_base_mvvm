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
import com.prady.app.base.helpers.LoadingDialog

open class LoadingUtils {
    companion object {
        private var loader: LoadingDialog? = null
        fun showDialog(
            context: Context?,
            isCancelable: Boolean
        ) {
            hideDialog()
            if (context != null) {
                try {
                    loader = LoadingDialog(context)
                    loader?.let { loadingDialog ->
                        loadingDialog.setCanceledOnTouchOutside(true)
                        loadingDialog.setCancelable(isCancelable)
                        loadingDialog.show()
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        fun hideDialog() {
            if (loader != null && loader?.isShowing!!) {
                loader = try {
                    loader?.dismiss()
                    null
                } catch (e: Exception) {
                    null
                }
            }
        }

    }
}