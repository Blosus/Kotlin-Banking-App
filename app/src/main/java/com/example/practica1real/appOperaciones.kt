package com.example.practica1real

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class appOperaciones : AppCompatActivity() {

    private lateinit var txtNumero1 : EditText
    private lateinit var txtNumero2 : EditText
    private lateinit var txtResultado: TextView
    private lateinit var spnOperaciones : Spinner
    private lateinit var btnCalcular : Button
    private lateinit var btnLimpiar : Button
    private lateinit var btnCerrar: Button
    private lateinit var image : ImageView
    private var pos : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_app_operaciones)
        iniciarComponentes()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun iniciarComponentes() {
        txtNumero1 = findViewById<EditText>(R.id.txtNumero1)
        txtNumero2 = findViewById<EditText>(R.id.txtNumero2)
        txtResultado = findViewById<TextView>(R.id.txtResultado)
        btnCalcular = findViewById<Button>(R.id.btnCalcular)
        btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
        btnCerrar = findViewById<Button>(R.id.btnCerrar)
        image = findViewById<ImageView>(R.id.image)

        val items = resources.getStringArray(R.array.operaciones)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        spnOperaciones.adapter = adapter
    }
}