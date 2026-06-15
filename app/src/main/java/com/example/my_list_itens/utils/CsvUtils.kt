package com.example.my_list_itens.utils

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import com.example.my_list_itens.data.local.entity.Item
import java.io.File

object CsvUtils {

    fun generateCsv(
        items: List<Item>,
        includeHeader: Boolean
    ): String {

        return buildString {

            if (includeHeader) {
                append(" Cliente, Item, Quantidade, Preço, Total, Data\n")
            }

            items.forEach { item ->
                append(
                    "${item.customer},"
                            + "${item.itemName},"
                            + "${item.quantity},"
                            + "${item.price}, "
                            + "${item.price * item.quantity},"
                            + "${item.date}\n",

                )
            }
        }
    }

    fun saveCsv(
        context: Context,
        fileName: String,
        content: String
    ): File {

        val file = File(
            context.cacheDir,
            "$fileName.csv"
        )

        file.writeText(content)

        return file
    }
    fun shareCsv(
    context: Context,
    file: File
    ) {

        val uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )

        val intent = Intent(Intent.ACTION_SEND).apply {

            type = "text/csv"

            putExtra(
                Intent.EXTRA_STREAM,
                uri
            )

            addFlags(
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
        }

        context.startActivity(
            Intent.createChooser(
                intent,
                "Compartilhar CSV"
            )
        )
    }
}

