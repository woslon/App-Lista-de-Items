package com.example.my_list_itens.ui.screen

import androidx.compose.foundation.background
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.my_list_itens.data.local.entity.Item
import com.example.my_list_itens.ui.viewmodel.ItemViewModel
import java.text.NumberFormat
import java.util.Locale
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun listScreen(
    viewModel: ItemViewModel = hiltViewModel()
) {

    var name by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var editingItem by remember { mutableStateOf<Item?>(null) }
    var search by remember { mutableStateOf("") }
    var isDialog by remember { mutableStateOf(false) }

    val items by viewModel.getAll().collectAsState(initial = emptyList())

    val format = NumberFormat.getCurrencyInstance(Locale( "pt", "BR"))
    val totalGeral = items.sumOf { it.price * it.quantity }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Itens") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF183729).copy(alpha = 0.9f),
                    titleContentColor = Color.White
                ),

                actions = {
                    IconButton(
                        onClick = { viewModel.deleteAll() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Deletar lista",
                            tint = Color.White
                        )
                    }
                }
            )

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { isDialog = true },
                containerColor = Color(0xFF183729),
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Adicionar",
                )
            }
        }
    ) { paddingValues ->

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            val isCompact = maxWidth < 420.dp

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 6.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedTextField(
                    value = search,
                    onValueChange = { search = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Pesquisar item") },
                    singleLine = true,
                    shape = RoundedCornerShape( size = 8.dp)
                )

                if (isDialog) {
                    Dialog(
                        onDismissRequest = { isDialog = false }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .widthIn(max = 500.dp)
                                .background(Color.White)
                                .padding(16.dp)
                        ) {


                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Adicionar Item",
                                    color = Color(0xFF183729),
                                    fontSize = 18.sp
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            if (isCompact) {

                                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

                                    OutlinedTextField(
                                        value = name,
                                        onValueChange = { name = it },
                                        label = { Text("Nome do item") },
                                        modifier = Modifier.fillMaxWidth(),
                                        singleLine = true,
                                        shape = RoundedCornerShape(8.dp)
                                    )

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {

                                        OutlinedTextField(
                                            value = quantity,
                                            onValueChange = { quantity = it },
                                            label = { Text("Qtd") },
                                            modifier = Modifier.weight(1f),
                                            singleLine = true,
                                            shape = RoundedCornerShape(8.dp)
                                        )

                                        OutlinedTextField(
                                            value = price,
                                            onValueChange = { price = it },
                                            label = { Text("Preço") },
                                            modifier = Modifier.weight(1f),
                                            singleLine = true,
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                    }
                                }

                            } else {

                                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

                                    OutlinedTextField(
                                        value = name,
                                        onValueChange = { name = it },
                                        label = { Text("Nome do item") },
                                        modifier = Modifier.fillMaxWidth(),
                                        singleLine = true,
                                        shape = RoundedCornerShape(8.dp)
                                    )

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {

                                        OutlinedTextField(
                                            value = quantity,
                                            onValueChange = { quantity = it },
                                            label = { Text("Qtd") },
                                            modifier = Modifier.weight(1f),
                                            singleLine = true,
                                            shape = RoundedCornerShape(8.dp)
                                        )

                                        OutlinedTextField(
                                            value = price,
                                            onValueChange = { price = it },
                                            label = { Text("Preço") },
                                            modifier = Modifier.weight(1f),
                                            singleLine = true,
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {

                                Button(
                                    onClick = { isDialog = false },
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(48.dp),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFFC62828).copy(alpha = 0.9f)
                                    )
                                ) {
                                    Text("Cancelar")
                                }

                                Button(
                                    onClick = {
                                        if (editingItem == null) {
                                            viewModel.add(
                                                Item(
                                                    name = name,
                                                    quantity = quantity.toIntOrNull() ?: 0,
                                                    price = price.replace(",", ".").toDoubleOrNull() ?: 0.0
                                                )
                                            )
                                        } else {
                                            viewModel.upsert(
                                                editingItem!!.copy(
                                                    name = name,
                                                    quantity = quantity.toIntOrNull() ?: 0,
                                                    price = price.replace(",", ".").toDoubleOrNull() ?: 0.0
                                                )
                                            )
                                            editingItem = null
                                        }

                                        name = ""
                                        quantity = ""
                                        price = ""
                                        isDialog = false
                                    },
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(48.dp),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFF183729).copy(alpha = 0.9f)
                                    )
                                ) {
                                    Text(if (editingItem == null) "+ Adicionar" else "Atualizar")
                                }
                            }
                        }
                    }
                }

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF2E7D32).copy(alpha = 0.1f)
                    ),
                    shape = RoundedCornerShape( size = 12.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Total da lista", fontSize = 16.sp)

                        Text(
                            text = format.format(totalGeral),
                            fontSize = 20.sp,
                            color = Color(0xFF2E7D32)
                        )
                    }
                }

                val filteredItems = items.filter {
                    it.name.contains( other = search, ignoreCase = true)
                }

                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    items(filteredItems) { item ->

                        val total = item.price * item.quantity

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape( size = 12.dp),
                            elevation = CardDefaults.cardElevation( defaultElevation = 4.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFFAFAFA)
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(all = 12.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon( imageVector = Icons.Default.Inventory,
                                        contentDescription = null, tint = Color(0xFF3E5654))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("Item: ${item.name}")

                                    Row (
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.End
                                    ) {
                                        IconButton(onClick = {
                                            editingItem = item
                                            name = item.name
                                            quantity = item.quantity.toString()
                                            price = item.price.toString()
                                            isDialog = true
                                        }) {
                                            Icon( imageVector = Icons.Default.Edit,
                                                contentDescription = null, tint = Color(0xFF9E9E9E))
                                        }
                                        IconButton(onClick = {
                                            viewModel.delete(item)
                                        }) {
                                            Icon( imageVector = Icons.Default.Delete,
                                                contentDescription = null, tint = Color(0xFFC62828))

                                        }
                                    }
                                }

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon( imageVector = Icons.Default.ShoppingCart,
                                        contentDescription = null, tint = Color(0xFF3E5654))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("Quantidade: ${item.quantity}")
                                }

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon( imageVector = Icons.Default.AttachMoney,
                                        contentDescription =  null, tint =  Color(0xFF3E5654))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("Preco: ${format.format(item.price)}")
                                }

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(48.dp)
                                        .background(
                                            Color(0xFF2E7D32).copy(alpha = 0.1f),
                                            shape = RoundedCornerShape(8.dp)
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Total: ${format.format(total)}",
                                        color = Color(0xFF2E7D32),
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}