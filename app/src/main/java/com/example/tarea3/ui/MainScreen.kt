package com.example.tarea3.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.GridOn
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.tarea3.ui.screens.HomeScreen
import com.example.tarea3.ui.screens.SumadoraScreen
import com.example.tarea3.ui.screens.TablaMultiplicarScreen
import com.example.tarea3.ui.screens.TraductorScreen
import kotlinx.coroutines.launch

enum class OpcionMenu(
    val titulo: String,
    val subtitulo: String,
    val icono: ImageVector
) {
    INICIO("Página Inicial", "Datos Personales", Icons.Default.Home),
    SUMADORA("Sumadora", "Suma de dos números", Icons.Default.Calculate),
    TRADUCTOR("Traductor", "Traductor de Números a Letras", Icons.Default.Translate),
    TABLA("Tabla de Multiplicar", "Tabla hasta el 13", Icons.Default.GridOn)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var opcionSeleccionada by remember { mutableStateOf(OpcionMenu.INICIO) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(24.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(36.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Tarea 3",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = "Menú Principal de Aplicación",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                    )
                }

                HorizontalDivider()

                Spacer(modifier = Modifier.height(12.dp))

                OpcionMenu.entries.forEach { opcion ->
                    NavigationDrawerItem(
                        icon = { Icon(opcion.icono, contentDescription = null) },
                        label = { Text(opcion.titulo) },
                        selected = opcion == opcionSeleccionada,
                        onClick = {
                            opcionSeleccionada = opcion
                            scope.launch { drawerState.close() }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Column {
                            Text(
                                text = opcionSeleccionada.titulo,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = opcionSeleccionada.subtitulo,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Abrir Menú"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainer
                    )
                )
            },
            bottomBar = {
                NavigationBar {
                    OpcionMenu.entries.forEach { opcion ->
                        NavigationBarItem(
                            icon = { Icon(opcion.icono, contentDescription = opcion.titulo) },
                            label = { Text(opcion.titulo) },
                            selected = opcion == opcionSeleccionada,
                            onClick = { opcionSeleccionada = opcion }
                        )
                    }
                }
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                when (opcionSeleccionada) {
                    OpcionMenu.INICIO -> HomeScreen()
                    OpcionMenu.SUMADORA -> SumadoraScreen()
                    OpcionMenu.TRADUCTOR -> TraductorScreen()
                    OpcionMenu.TABLA -> TablaMultiplicarScreen()
                }
            }
        }
    }
}
