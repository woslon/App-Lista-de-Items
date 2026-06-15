package com.example.my_list_itens.utils

import android.app.AlertDialog
import android.content.Context


fun Context.Alert(msg : String){
    AlertDialog.Builder(this)
        .setMessage(msg)
        .setPositiveButton("ok", null)
        .show()
}