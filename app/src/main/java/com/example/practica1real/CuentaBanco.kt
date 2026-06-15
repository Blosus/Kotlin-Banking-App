package com.example.practica1real

import java.io.Serializable

class CuentaBanco : Serializable {
    var numCuenta: String = ""
    var nombre: String = ""
    var banco: String = ""
    var saldo: Float = 0.0f

    constructor() {
        this.numCuenta = ""
        this.nombre = ""
        this.banco = ""
        this.saldo = 0.0f
    }

    constructor(numCuenta: String, nombre: String, banco: String, saldo: Float) {
        this.numCuenta = numCuenta
        this.nombre = nombre
        this.banco = banco
        this.saldo = saldo
    }

    fun obtenerSaldo(): Float {
        return this.saldo
    }

    fun hacerDeposito(cantidad: Float) {
        this.saldo += cantidad
    }

    fun retirarDinero(cantidad: Float): Boolean {
        if (cantidad <= this.saldo) {
            this.saldo -= cantidad
            return true
        }
        return false
    }
}

