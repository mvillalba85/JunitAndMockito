package org.mvillalba.junitapp.ejemplo.models;

import org.junit.jupiter.api.Test;
import org.mvillalba.junitapp.ejemplo.exceptions.DineroInsuficienteException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {

    @Test
    void testTitular() {
        //Given
        Cuenta cuenta = new Cuenta();
        cuenta.setTitular("Miguel");

        //When
        String titular = cuenta.getTitular();

        //Then
        assertEquals("Miguel", titular);

    }

    @Test
    void testSaldoCuenta() {
        //Given
        Cuenta cuenta = new Cuenta("Miguel", new BigDecimal("1000.12345"));

        //When
        BigDecimal saldo = cuenta.getSaldo();

        //Then
        assertNotNull(saldo);
        assertEquals(0, saldo.compareTo(new java.math.BigDecimal("1000.12345")));
        assertEquals(1000.12345, cuenta.getSaldo().doubleValue());
        assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
    }

    @Test
    void testReferenciaCuenta() {
        //Given
        Cuenta cuenta = new Cuenta("Miguel", new BigDecimal("1000.12345"));
        Cuenta cuenta2 = new Cuenta("Miguel", new BigDecimal("1000.12345"));

        //Then
//        assertNotEquals(cuenta, cuenta2);
        assertEquals(cuenta, cuenta2);
//        assertNotSame(cuenta, cuenta2);
    }

    @Test
    void testDebitoCuenta() {
        //Given
        Cuenta cuenta = new Cuenta("Miguel", new BigDecimal("1000.12345"));

        //When
        cuenta.debito(new BigDecimal("100"));

        //Then
        assertNotNull(cuenta.getSaldo());
        assertEquals(900, cuenta.getSaldo().intValue());
        assertEquals("900.12345", cuenta.getSaldo().toPlainString());
        assertEquals(0, cuenta.getSaldo().compareTo(new BigDecimal("900.12345")));
    }

    @Test
    void testCreditoCuenta() {
        //Given
        Cuenta cuenta = new Cuenta("Miguel", new BigDecimal("1000.12345"));

        //When
        cuenta.credito(new BigDecimal("100"));

        //Then
        assertNotNull(cuenta.getSaldo());
        assertEquals(1100, cuenta.getSaldo().intValue());
        assertEquals("1100.12345", cuenta.getSaldo().toPlainString());
        assertEquals(0, cuenta.getSaldo().compareTo(new BigDecimal("1100.12345")));
    }

    @Test
    void testDineroInsuficienteExceptionCuenta() {
        //Given
        Cuenta cuenta = new Cuenta("Miguel", new BigDecimal("1000.12345"));

        //When
        Exception exception = assertThrows(DineroInsuficienteException.class, () -> {
            cuenta.debito(new BigDecimal("1500"));
        });

        //Then
        String actualMessage = exception.getMessage();
        String esperadoMessage = "Saldo insuficiente en la cuenta de Miguel";
        assertEquals(esperadoMessage, actualMessage);
    }

}