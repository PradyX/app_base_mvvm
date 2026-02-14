/*
 * *
 *  * Created by prady on 14/02/26, 2:28 pm, $today.time
 *  * Copyright (c) 2026 . All rights reserved.
 *  * Last modified 14/02/26, 2:28 pm, $file.lastModified.time
 *
 *
 */

package com.prady.app.base.helpers

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.LinearLayout
import com.prady.app.base.R
import com.prady.app.base.databinding.ViewLoadingAnimationBinding

class LoadingDialog(context: Context) : Dialog(context) {

    private lateinit var binding: ViewLoadingAnimationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ViewLoadingAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window?.setLayout(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        window?.setBackgroundDrawableResource(R.color.transparent_gray)
    }
}