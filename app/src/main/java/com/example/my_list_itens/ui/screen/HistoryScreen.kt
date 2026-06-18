package com.example.my_list_itens.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowLeft
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.my_list_itens.ui.viewmodel.HistoryViewModel
import com.example.my_list_itens.utils.CsvUtils.shareCsv
import androidx.core.net.toUri
import com.example.my_list_itens.utils.Alert

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HistoryScreen(
    navController: NavController,
    viewModel: HistoryViewModel = hiltViewModel()
) {

    val historyList by viewModel.getAll().collectAsState(initial = emptyList())
    val context = LocalContext.current

    var search by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Histórico CSV") },

                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowLeft,
                            contentDescription = "Voltar",
                            tint = Color.White
                        )
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF183729),
                    titleContentColor = Color.White
                ),

                actions = {
                    IconButton(
                        onClick = {
                            context.Alert(
                                title = "Atenção!",
                                msg = "Tem certeza que deseja apagar todo histórico de arquivos?",
                                onConfirm = {
                                    viewModel.deleteAll()
                                }
                            )
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Excluir",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            OutlinedTextField(
                value = search,
                onValueChange = { search = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Pesquisar CSV") },
                singleLine = true,
                shape = RoundedCornerShape(8.dp)
            )

            val filtered = historyList.filter {
                it.fileName.contains(search, ignoreCase = true)
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF2E7D32).copy(alpha = 0.1f)
                )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(). padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Total de arquivos")
                    Text(
                        text = "${filtered.size}",
                        color = Color(0xFF2E7D32),
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(filtered) { history ->

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    ) {

                        Column(
                            modifier = Modifier.padding(12.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {

                                Column {
                                    Row {
                                        Icon(Icons.Default.Description, null)
                                        Spacer(Modifier.width(6.dp))
                                        Text("Arquivo: ${history.fileName}")
                                    }

                                    Row {
                                        Icon(Icons.Default.DateRange, null)
                                        Spacer(Modifier.width(6.dp))
                                        Text("Data: ${history.date}")
                                    }
                                }


                                Row {
                                    IconButton(onClick = {
                                        viewModel.delete(history)
                                    }) {
                                        Icon( imageVector = Icons.Default.Delete,
                                            contentDescription = "Apagar item",
                                            tint = Color.Red)
                                    }

                                    IconButton(
                                        onClick = {
                                            val uri = history.fileUri.toUri()
                                            shareCsv(context ,  uri)

                                        }
                                    ) {
                                        Icon( imageVector = Icons.Default.Share,
                                            contentDescription = "Compartilhar Histórico",
                                             tint = Color.DarkGray
                                        )
                                    }

                                }


                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        Color(0xFF183729).copy(alpha = 0.1f),
                                        RoundedCornerShape(8.dp)
                                    )
                                    .padding(10.dp)
                            ) {
                                Text(
                                    "Status: Exportado",
                                    color = Color(0xFF183729),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}