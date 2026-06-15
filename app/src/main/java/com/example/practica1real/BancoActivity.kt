package com.example.practica1real

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BancoActivity : AppCompatActivity() {

    lateinit var txtNumeroCuenta: EditText
    lateinit var txtNombreCliente: EditText
    lateinit var txtBanco: EditText
    lateinit var txtSaldo: EditText
    lateinit var txtCantidad: EditText
    lateinit var rdgMovimientos: RadioGroup
    lateinit var btnRegistrar: Button
    lateinit var btnMovimiento: Button
    lateinit var btnCerrar: Button
    var cuenta = CuentaBanco()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_banco)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        iniciarComponentes()
        eventosClick()
    }

    private fun iniciarComponentes() {
        txtNumeroCuenta = findViewById(R.id.txtNumeroCuenta)
        txtNombreCliente = findViewById(R.id.txtNombreCliente)
        txtBanco = findViewById(R.id.txtBanco)
        txtSaldo = findViewById(R.id.txtSaldo)
        txtCantidad = findViewById(R.id.txtCantidad)
        rdgMovimientos = findViewById(R.id.rdgMovimientos)
        btnRegistrar = findViewById(R.id.btnRegistrar)
        btnMovimiento = findViewById(R.id.btnMovimiento)
        btnCerrar = findViewById(R.id.btnCerrar)
    }

    private fun eventosClick() {
        btnRegistrar.setOnClickListener { registrarCuenta() }
        btnMovimiento.setOnClickListener { aplicarMovimiento() }
        btnCerrar.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("BancoActivity")
            builder.setMessage("¿Deseas cerrar la aplicacion?")
            builder.setPositiveButton("Aceptar"){
                    Dialog , which -> finish()
            }
            builder.setNegativeButton("Cancelar"){
                    Dialog , which ->
                Toast.makeText(applicationContext, "Continuamos en la app",
                    Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }
    }

    private fun registrarCuenta() {
        val numCuenta = txtNumeroCuenta.text.toString().trim()
        val nombre = txtNombreCliente.text.toString().trim()
        val banco = txtBanco.text.toString().trim()
        val saldoTexto = txtSaldo.text.toString().trim()

        if (numCuenta.isEmpty() || nombre.isEmpty() || banco.isEmpty() || saldoTexto.isEmpty()) {
            Toast.makeText(this, "Debes capturar Número de cuenta, Nombre, Banco y Saldo", Toast.LENGTH_SHORT).show()
            return
        }

        val saldo = saldoTexto.toFloat()

        if (saldo < 0) {
            Toast.makeText(this, "El saldo no puede ser negativo", Toast.LENGTH_SHORT).show()
            return
        }

        cuenta = CuentaBanco(numCuenta, nombre, banco, saldo)
        Toast.makeText(this, "Cuenta registrada correctamente", Toast.LENGTH_SHORT).show()
    }

    private fun aplicarMovimiento() {
        val movimientoSeleccionado = rdgMovimientos.checkedRadioButtonId

        if (movimientoSeleccionado == R.id.rdbConsSaldo) {
            txtSaldo.setText(cuenta.obtenerSaldo().toString())
            return
        }

        val cantidadTexto = txtCantidad.text.toString().trim()
        if (cantidadTexto.isEmpty()) {
            Toast.makeText(this, "Debes capturar la cantidad", Toast.LENGTH_SHORT).show()
            return
        }

        val cantidad = cantidadTexto.toFloat()

        if (cantidad <= 0) {
            Toast.makeText(this, "La cantidad debe ser mayor a cero", Toast.LENGTH_SHORT).show()
            return
        }

        when (movimientoSeleccionado) {
            R.id.rdbDeposito -> {
                cuenta.hacerDeposito(cantidad)
                Toast.makeText(this, "Deposito realizado", Toast.LENGTH_SHORT).show()
            }
            R.id.rdbRetiro -> {
                val exito = cuenta.retirarDinero(cantidad)
                if (exito) {
                    Toast.makeText(this, "Retiro realizado", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Saldo insuficiente para realizar el retiro", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}