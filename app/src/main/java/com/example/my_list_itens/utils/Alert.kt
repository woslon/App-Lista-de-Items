package com.example.my_list_itens.utils

import android.app.AlertDialog
import android.content.Context


fun Context.Alert(
    msg: String,
    title: String,
    onConfirm: (() -> Unit)? = null
) {

    val builder = AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(msg)

    if (onConfirm != null) {
        builder.setPositiveButton("Confirmar") { _, _ ->
            onConfirm()
        }
        builder.setNegativeButton("Cancelar", null)
    } else {
        builder.setPositiveButton("OK", null)
    }

    builder.show()
}