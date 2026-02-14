/*
 * *
 *  * Created by prady on 14/02/26, 2:28 pm, $today.time
 *  * Copyright (c) 2026 . All rights reserved.
 *  * Last modified 14/02/26, 2:28 pm, $file.lastModified.time
 *
 *
 */

package com.prady.app.base.utils

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.airbnb.lottie.LottieAnimationView
import com.prady.app.base.R

object AlertDialogUtil {
    /* This is an extension function. It is used to extend the functionality of a class. */
    fun Context.showAlertDialog(
        title: String? = null,
        message: String,
        posBtnText: String? = null,
        negBtnText: String? = null,
        showNegBtn: Boolean = true,
        callback: () -> Unit
    ) {
        /* Creating an alert dialog with the given parameters. */
        AlertDialog.Builder(this, R.style.AlertDialogTheme).also {
            it.setTitle(title ?: getString(R.string.alert))
            it.setMessage(message)
            it.setPositiveButton(posBtnText ?: getString(R.string.yes)) { _, _ ->
                callback()
            }
            if (showNegBtn) {
                it.setNegativeButton(negBtnText ?: getString(R.string.no)) { dialog, _ ->
                    dialog.dismiss()
                }
            }
        }.create().show()
    }

    fun Context.apiAlertDialog(
        isError: Boolean,
        title: String,
        subTitle: String,
        action: () -> Unit
    ) {
        val dialog = Dialog(this)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_header_api_common)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window?.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT

        val btnClose = dialog.findViewById<TextView>(R.id.btn_close)
        btnClose.setOnClickListener {
            action()
            dialog.dismiss()
        }

        val animation = dialog.findViewById<LottieAnimationView>(R.id.animationView)
        if (isError) {
            animation.setAnimation(R.raw.no_data)
            btnClose.setBackgroundColor(getColorFromAttr(com.google.android.material.R.attr.colorOnError))
        } else {
            animation.setAnimation(R.raw.success)
        }

        val tvTitle = dialog.findViewById<TextView>(R.id.tv_title)
        if (title.isNullOrEmpty()) {
            tvTitle.visibility = View.GONE
        } else {
            tvTitle.visibility = View.VISIBLE
            tvTitle.text = title
        }

        val tvSubtitle = dialog.findViewById<TextView>(R.id.tv_sub_title)
        if (subTitle.isNullOrEmpty()) {
            tvSubtitle.visibility = View.GONE
        } else {
            tvSubtitle.visibility = View.VISIBLE
            tvSubtitle.text = subTitle
        }

        dialog.show()
        dialog.window?.attributes = lp
    }

    fun Context.apiErrorDialog(errMsg: String) {
        val dialog = Dialog(this)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_header_api_failed)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window?.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT

        val title = dialog.findViewById<TextView>(R.id.tv_title)

        val subtitle = dialog.findViewById<TextView>(R.id.tv_sub_title)
        subtitle.text = errMsg

        val btnClose = dialog.findViewById<TextView>(R.id.btn_close)
        btnClose.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
        dialog.window?.attributes = lp
    }

//    fun Context.fullScreenImgDialog(imgUrl: String) {
//        val dialog = Dialog(this)
//        dialog.setCancelable(true)
//        dialog.setContentView(R.layout.dialog_header_full_screeen_img)
//        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
//
//        val lp = WindowManager.LayoutParams()
//        lp.copyFrom(dialog.window?.attributes)
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT
//        lp.height = WindowManager.LayoutParams.MATCH_PARENT
//
//        dialog.findViewById<MaterialButton>(R.id.btn_close)?.setOnClickListener {
//            dialog.dismiss()
//        }
//
//        dialog.findViewById<ImageView>(R.id.iv_img).load(imgUrl) {
//            placeholder(R.drawable.logo)
//            error(R.drawable.logo)
//        }
//
//        dialog.show()
//        dialog.window?.attributes = lp
//    }
}