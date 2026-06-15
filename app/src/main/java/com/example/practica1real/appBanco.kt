package com.example.practica1real

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class appBanco : AppCompatActivity() {

    lateinit var txtUser: EditText
    lateinit var txtPassword: EditText
    lateinit var btnEntrar: Button
    lateinit var btnRegresar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_app_banco)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtUser = findViewById(R.id.txtUser)
        txtPassword = findViewById(R.id.txtPassword)
        btnEntrar = findViewById(R.id.btnEntrar)
        btnRegresar = findViewById(R.id.btnRegresar)

        btnEntrar.setOnClickListener {
            validarLogin()
        }

        btnRegresar.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("appBanco")
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

    private fun validarLogin() {
        val usuario = txtUser.text.toString().trim()
        val password = txtPassword.text.toString().trim()

        if (usuario.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Debes capturar usuario y contraseña", Toast.LENGTH_SHORT).show()
            return
        }

        val usuarioCorrecto = getString(R.string.user)
        val passwordCorrecto = getString(R.string.pass)

        if (usuario == usuarioCorrecto && password == passwordCorrecto) {
            val intent = Intent(this, BancoActivity::class.java)
            intent.putExtra("usuario", usuario)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
        }
    }
}