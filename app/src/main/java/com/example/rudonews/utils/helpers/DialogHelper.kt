package com.example.rudonews.utils.helpers

import android.app.AlertDialog
import android.content.Context
import com.example.rudonews.R

class DialogHelper {

    companion object {

        fun showAlertDialog(context: Context, message: String) {
            AlertDialog.Builder(context, R.style.dialogButtonStyle)
                .setMessage(message)
                .setPositiveButton("ACEPTAR") { _, _ ->
                }
                .show()

        }
    }
}
