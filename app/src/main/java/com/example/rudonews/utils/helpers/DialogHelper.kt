package com.example.rudonews.utils.helpers

import android.content.Context
import com.example.rudonews.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DialogHelper {

    companion object {

        fun showAlertDialog(context: Context, message: String) {
            MaterialAlertDialogBuilder(context)
                .setMessage(message)
                .setPositiveButton("ACEPTAR") { dialog, which ->
                }
                .show()

        }
    }
}
