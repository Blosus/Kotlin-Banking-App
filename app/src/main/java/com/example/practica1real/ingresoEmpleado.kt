package com.example.practica1real

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ingresoEmpleado : AppCompatActivity() {

    private lateinit var inputNombre: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ingreso_empleado)

        inputNombre = findViewById(R.id.txtNombre)
        val btnEntrar = findViewById<Button>(R.id.btnEntrar)
        val btnCerrar = findViewById<Button>(R.id.btnSalir)

        btnEntrar.setOnClickListener {
            Entrar()
        }

        btnCerrar.setOnClickListener(View.OnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("ingresoEmpleado")
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
        })

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun Entrar(){
        if(inputNombre.text.isEmpty()){
            makeText(applicationContext , "Nombre no puede ser vacio", Toast.LENGTH_SHORT).show()
            return
        }
        if(inputNombre.text.toString().any { it.isDigit() }){
            Toast.makeText(applicationContext , "Nombre no puede contener Numeros", Toast.LENGTH_SHORT).show()
            return
        }
        val  nombre = inputNombre.text.toString()
        val intent = Intent(this, calculoNomina::class.java)
        intent.putExtra("nombre", nombre)
        startActivity(intent)
    }
}