package com.example.tarea3.utils

object SpanishNumberUtils {

    /**
     * Convierte un número entero del 1 al 1000 a su representación en letras en español.
     * Algoritmo puramente local sin uso de APIs externas.
     */
    fun numeroALetras(numero: Int): String {
        if (numero !in 1..1000) {
            return "Por favor ingrese un número entero entre 1 y 1000."
        }
        if (numero == 1000) return "mil"
        if (numero == 100) return "cien"

        val unidades = arrayOf("", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve")
        val especiales10 = arrayOf("diez", "once", "doce", "trece", "catorce", "quince", "dieciséis", "diecisiete", "dieciocho", "diecinueve")
        val veintes = arrayOf("veinte", "veintiuno", "veintidós", "veintitrés", "veinticuatro", "veinticinco", "veintiséis", "veintisiete", "veintiocho", "veintinueve")
        val decenas = arrayOf("", "", "", "treinta", "cuarenta", "cincuenta", "sesenta", "setenta", "ochenta", "noventa")
        val centenas = arrayOf("", "ciento", "doscientos", "trescientos", "cuatrocientos", "quinientos", "seiscientos", "setecientos", "ochocientos", "novecientos")

        var n = numero
        val c = n / 100
        n %= 100
        val d = n / 10
        val u = n % 10

        val parteCentena = if (c > 0) centenas[c] else ""
        val parteDecenaUnidad = when {
            n == 0 -> ""
            n in 1..9 -> unidades[n]
            n in 10..19 -> especiales10[n - 10]
            n in 20..29 -> veintes[n - 20]
            else -> {
                val dec = decenas[d]
                if (u == 0) dec else "$dec y ${unidades[u]}"
            }
        }

        return when {
            parteCentena.isEmpty() -> parteDecenaUnidad
            parteDecenaUnidad.isEmpty() -> parteCentena
            else -> "$parteCentena $parteDecenaUnidad"
        }
    }
}
