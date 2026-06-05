package com.example.practica1real

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class ClienteActivity : AppCompatActivity() {
    private lateinit var txtCliente: TextView
    private lateinit var btnIngresar: Button
    private lateinit var btnRegresar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cliente)
        iniciarComponentes()
        eventosClick()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    public fun iniciarComponentes(){
        txtCliente = findViewById<EditText>(R.id.txtCliente)
        btnRegresar = findViewById<Button>(R.id.btnRegresar)
        btnIngresar = findViewById<Button>(R.id.btnEntrar)
    }

    public fun eventosClick(){
        btnIngresar.setOnClickListener({
            if(txtCliente.text.toString().contentEquals("")){
                Toast.makeText(this, "Falto capturar el nombre del cliente", Toast.LENGTH_SHORT).show()
                txtCliente.requestFocus()
            } else {
                val intent = Intent(this, CotizacionActivity::class.java)
                intent.putExtra("cliente", txtCliente.text.toString())
                startActivity(intent)
            }
        })

        btnRegresar.setOnClickListener({
            finish()
        })
    }
}