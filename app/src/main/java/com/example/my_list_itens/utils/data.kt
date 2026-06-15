package com.example.my_list_itens.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun getToday(): String {
    val today = LocalDate.now()

    return today.format(
        DateTimeFormatter.ofPattern("dd/MM/yyyy")
    )
}