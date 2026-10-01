package com.example.tarea3

import com.example.tarea3.utils.SpanishNumberUtils
import org.junit.Assert.assertEquals
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun testNumeroALetras() {
        assertEquals("uno", SpanishNumberUtils.numeroALetras(1))
        assertEquals("dieciséis", SpanishNumberUtils.numeroALetras(16))
        assertEquals("veintidós", SpanishNumberUtils.numeroALetras(22))
        assertEquals("treinta y cinco", SpanishNumberUtils.numeroALetras(35))
        assertEquals("cien", SpanishNumberUtils.numeroALetras(100))
        assertEquals("ciento uno", SpanishNumberUtils.numeroALetras(101))
        assertEquals("quinientos quince", SpanishNumberUtils.numeroALetras(515))
        assertEquals("setecientos veintiséis", SpanishNumberUtils.numeroALetras(726))
        assertEquals("novecientos noventa y nueve", SpanishNumberUtils.numeroALetras(999))
        assertEquals("mil", SpanishNumberUtils.numeroALetras(1000))
    }
}
