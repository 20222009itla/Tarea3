package com.example.tarea3.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SumadoraScreen(modifier: Modifier = Modifier) {
    var numero1Text by remember { mutableStateOf("") }
    var numero2Text by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf<Double?>(null) }
    var errorMensaje by remember { mutableStateOf<String?>(null) }

    fun sumar() {
        val num1 = numero1Text.toDoubleOrNull()
        val num2 = numero2Text.toDoubleOrNull()

        if (num1 == null || num2 == null) {
            errorMensaje = "Por favor ingrese números válidos en ambos campos."
            resultado = null
        } else {
            errorMensaje = null
            resultado = num1 + num2
        }
    }

    fun limpiar() {
        numero1Text = ""
        numero2Text = ""
        resultado = null
        errorMensaje = null
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
            text = "Sumadora",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Ingrese dos números para realizar la suma",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(28.dp))

        // Formulario
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
                    value = numero1Text,
                    onValueChange = {
                        numero1Text = it
                        errorMensaje = null
                    },
                    label = { Text("Primer Número") },
                    placeholder = { Text("Ej. 15") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = numero2Text,
                    onValueChange = {
                        numero2Text = it
                        errorMensaje = null
                    },
                    label = { Text("Segundo Número") },
                    placeholder = { Text("Ej. 27") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedButton(
                        onClick = { limpiar() },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = null,
                            modifier = Modifier.padding(end = 6.dp)
                        )
                        Text("Limpiar")
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Button(
                        onClick = { sumar() },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            modifier = Modifier.padding(end = 6.dp)
                        )
                        Text("Sumar")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Error si hay
        errorMensaje?.let { mensaje ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer
                )
            ) {
                Text(
                    text = mensaje,
                    color = MaterialTheme.colorScheme.onErrorContainer,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        // Tarjeta de Resultado
        resultado?.let { res ->
            val num1 = numero1Text.toDoubleOrNull() ?: 0.0
            val num2 = numero2Text.toDoubleOrNull() ?: 0.0

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
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
                        imageVector = Icons.Default.Calculate,
                        contentDescription = "Resultado",
                        modifier = Modifier.height(48.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Resultado de la Suma",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    val formattedRes = if (res % 1.0 == 0.0) {
                        res.toLong().toString()
                    } else {
                        res.toString()
                    }

                    val formatted1 = if (num1 % 1.0 == 0.0) num1.toLong().toString() else num1.toString()
                    val formatted2 = if (num2 % 1.0 == 0.0) num2.toLong().toString() else num2.toString()

                    Text(
                        text = "$formatted1 + $formatted2 = $formattedRes",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Total: $formattedRes",
                        style = MaterialTheme.typography.displaySmall,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 36.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}
