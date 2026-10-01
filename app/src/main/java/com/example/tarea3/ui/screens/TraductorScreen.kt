package com.example.tarea3.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tarea3.utils.SpanishNumberUtils

@Composable
fun TraductorScreen(modifier: Modifier = Modifier) {
    var numeroInputText by remember { mutableStateOf("") }
    var numeroProcesadoText by remember { mutableStateOf<String?>(null) }

    val numeroInt = numeroProcesadoText?.toIntOrNull()
    val resultadoLetras = when {
        numeroProcesadoText == null -> null
        numeroProcesadoText!!.isBlank() -> "Por favor ingrese un número."
        numeroInt == null -> "Por favor ingrese un número entero válido."
        else -> SpanishNumberUtils.numeroALetras(numeroInt)
    }

    fun realizarTraduccion() {
        numeroProcesadoText = numeroInputText
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Traductor de Números",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Conversión de números (1 a 1000) a letras en español",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(28.dp))

        // Card de Entrada
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                OutlinedTextField(
                    value = numeroInputText,
                    onValueChange = { input ->
                        if (input.length <= 4 && input.all { it.isDigit() }) {
                            numeroInputText = input
                        }
                    },
                    label = { Text("Número (1 al 1000)") },
                    placeholder = { Text("Ej. 515") },
                    leadingIcon = { Icon(Icons.Default.Translate, contentDescription = null) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = { realizarTraduccion() },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Translate,
                        contentDescription = null,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text("Traducir a Letras")
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Tarjeta de Resultado en Letras
        resultadoLetras?.let { textoLetras ->
            val esExito = numeroInt != null && numeroInt in 1..1000

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (esExito) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.errorContainer
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Translate,
                        contentDescription = "Traducción",
                        modifier = Modifier.height(48.dp),
                        tint = if (esExito) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.error
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = if (esExito) "Traducción en Letras" else "Aviso",
                        style = MaterialTheme.typography.titleMedium,
                        color = if (esExito) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onErrorContainer
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = textoLetras.replaceFirstChar { it.uppercase() },
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        color = if (esExito) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onErrorContainer
                    )

                    if (esExito) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Número: $numeroInt",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                        )
                    }
                }
            }
        }
    }
}
