package org.mvillalba.junitapp.ejemplo.models;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Banco {
    private String nombre;
    private List<Cuenta> cuentas;

    public Banco() {
        this.cuentas = new ArrayList<>();
    }

    public Banco(String nombre) {
        this.nombre = nombre;
        this.cuentas = new ArrayList<>();
    }

    public void addCuenta(Cuenta cuenta) {
        this.cuentas.add(cuenta);
        cuenta.setBanco(this);
    }

    public void removeCuenta(Cuenta cuenta) {
        this.cuentas.remove(cuenta);
        cuenta.setBanco(null);
    }

    public void tranferir(Cuenta cuentaOrigen, Cuenta cuentaDestino, BigDecimal monto){
        cuentaOrigen.debito(monto);
        cuentaDestino.credito(monto);
    }
}
