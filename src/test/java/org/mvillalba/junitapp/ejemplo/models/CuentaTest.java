package org.mvillalba.junitapp.ejemplo.models;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mvillalba.junitapp.ejemplo.exceptions.DineroInsuficienteException;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {
    Cuenta cuenta;

    @BeforeEach
    void init() {
        //Given
        cuenta = new Cuenta("Miguel", new BigDecimal("1000.12345"));
    }

    @AfterEach
    void tearDown() {
        System.out.println("Finalizando el test...");
    }

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
        Cuenta cuenta2 = new Cuenta("Miguel", new BigDecimal("1000.12345"));

        //Then
//        assertNotEquals(cuenta, cuenta2);
        assertEquals(cuenta, cuenta2);
//        assertNotSame(cuenta, cuenta2);
    }

    @Test
    void testDebitoCuenta() {

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

    @ParameterizedTest(name = "Debito cuenta {0} - {argumentsWithNames} ")
    @ValueSource(strings = {"100", "200", "300", "500", "700", "1000.12345"})
    void testDebitoCuentaValueSource(String monto) {
        //When
        cuenta.debito(new BigDecimal(monto));

        //Then
        assertNotNull(cuenta.getSaldo());
        assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) >= 0);
    }

    @ParameterizedTest(name = "Debito cuenta {0} - {argumentsWithNames} ")
    @CsvSource({"1, 100", "2, 200", "3, 300", "4, 500", "5, 700", "6, 1000.12345"})
    void testDebitoCuentaCsvSource(String index, String monto) {
        System.out.println("index = " + index + " -> monto = " + monto);
        //When
        cuenta.debito(new BigDecimal(monto));

        //Then
        assertNotNull(cuenta.getSaldo());
        assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) >= 0);

    }

    @ParameterizedTest(name = "Debito cuenta {0} - {argumentsWithNames} ")
    @CsvFileSource(resources = "/data.csv")
    void testDebitoCuentaCsvFileSource(String monto) {
        //When
        cuenta.debito(new BigDecimal(monto));

        //Then
        assertNotNull(cuenta.getSaldo());
        assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) >= 0);
    }

    @ParameterizedTest(name = "numero {index} ejecutando con valor {0} - {argumentsWithNames} ")
    @MethodSource("montoList")
    void testDebitoCuentaMethodSource(String monto) {
        //When
        cuenta.debito(new BigDecimal(monto));

        //Then
        assertNotNull(cuenta.getSaldo());
        assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) >= 0);
    }

    private static List<String> montoList() {
        return List.of("100", "200", "300", "500", "700", "1000.12345");
    }

    
}