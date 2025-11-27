package org.mvillalba.junitapp.ejemplo.models;

import lombok.Data;
import org.mvillalba.junitapp.ejemplo.exceptions.DineroInsuficienteException;

import java.math.BigDecimal;

@Data
public class Cuenta {
    private String titular;
    private BigDecimal saldo;

    private Banco banco;

    public Cuenta() {
    }

    public Cuenta(String titular, BigDecimal saldo) {
        this.titular = titular;
        this.saldo = saldo;
    }

    public void debito(BigDecimal monto) {
        BigDecimal nuevoSaldo = this.saldo.subtract(monto);
        if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new DineroInsuficienteException("Saldo insuficiente en la cuenta de " + this.titular);
        }
        this.saldo = this.saldo.subtract(monto);
    }

    public void credito(BigDecimal monto) {
        this.saldo = this.saldo.add(monto);
    }
}
