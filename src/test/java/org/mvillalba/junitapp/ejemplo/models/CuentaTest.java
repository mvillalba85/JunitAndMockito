package org.mvillalba.junitapp.ejemplo.models;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mvillalba.junitapp.ejemplo.exceptions.DineroInsuficienteException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {

    @Test
    @DisplayName("Test para el nombre del titular de la cuenta")
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
    @DisplayName("Test para el saldo de la cuenta")
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
    @DisplayName("Test credito cuenta")
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

    @Test
    @Disabled
    @DisplayName("Test relacion entre Banco y Cuenta con Assert All")
    void testRelacionBancoCuenta() {
        fail();
        Cuenta cuenta1 = new Cuenta("Miguel", new BigDecimal("2500"));
        Cuenta cuenta2 = new Cuenta("Mariano", new BigDecimal("1500.8989"));

        Banco banco = new Banco("Banco del Estado");
        banco.addCuenta(cuenta1);
        banco.addCuenta(cuenta2);
        banco.tranferir(cuenta2, cuenta1, new BigDecimal("500"));

        assertAll(() -> {
            assertEquals("1000.8989", cuenta2.getSaldo().toPlainString());
        }, () -> {
            assertEquals("3000", cuenta1.getSaldo().toPlainString());
        }, () -> {
            assertEquals(2, banco.getCuentas().size());
        },
                () -> {
            assertEquals("Banco del Estado", cuenta1.getBanco().getNombre());
        },
        () -> {
            assertEquals("Miguel", banco.getCuentas().stream()
                            .filter(c -> c.getTitular().equals("Miguel"))
                                    .findFirst()
                                            .get().getTitular());
        },
        () -> {
            assertTrue(banco.getCuentas().stream().anyMatch(c -> c.getTitular().equals("Mariano")));
        },
        () -> {
            assertTrue(banco.getCuentas().stream().anyMatch(c -> c.getTitular().equals("Miguel")));
        }
        );

    }

}