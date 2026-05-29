package com.example.practica1real

import android.os.Bundle
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
    private lateinit var btnSalir : Button
    private lateinit var image : ImageView
    private var pos : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_app_operaciones)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}