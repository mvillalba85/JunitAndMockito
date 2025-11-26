package org.mvillalba.junitapp.ejemplo.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Cuenta {
    private String titular;
    private BigDecimal saldo;

    public Cuenta() {
    }

    public Cuenta(String titular, BigDecimal saldo) {
        this.titular = titular;
        this.saldo = saldo;
    }
}
