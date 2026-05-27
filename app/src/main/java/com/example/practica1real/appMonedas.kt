package com.example.practica1real

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class appMonedas : AppCompatActivity() {

    private lateinit var txtCantidad : EditText
    private lateinit var spnMonedas : Spinner
    private lateinit var txtResultado : TextView
    private lateinit var btnConvertir : Button
    private lateinit var btnLimpiar : Button
    private lateinit var btnSalir : Button
    private var pos : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_app_monedas)

        iniciarComponentes()
        eventosClick()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun iniciarComponentes(){
        txtCantidad = findViewById<EditText>(R.id.txtCantidad)
        spnMonedas = findViewById<Spinner>(R.id.spnMonedas)
        txtResultado = findViewById<TextView>(R.id.txtResultado)
        btnConvertir = findViewById<Button>(R.id.btnConvertir)
        btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
        btnSalir = findViewById<Button>(R.id.btnSalir)

        // obtener los datos del array-string para ponerlos en el adapter

        val items = resources.getStringArray(R.array.monedas)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        spnMonedas.adapter = adapter
    }

    fun eventosClick(){

        spnMonedas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                pos = p2
            }

            override fun equals(other: Any?): Boolean {
                return super.equals(other)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        btnConvertir.setOnClickListener {
            //validar
            if(txtCantidad.text.toString().contentEquals("")){
                Toast.makeText(this, R.string.strMensaje, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            var dolarA : Float = R.string.dolarA.toString().toFloat()
            var dolarC : Float = R.string.dolarC.toString().toFloat()
            var euro : Float = R.string.euro.toString().toFloat()
            var libra : Float = R.string.libra.toString().toFloat()
            var cantMx : Float = txtCantidad.text.toString().toFloat()

            val resultado = when(pos){
                0 -> cantMx/dolarA
                1 -> cantMx/dolarC
                2 -> cantMx/euro
                3 -> cantMx/libra
                else -> 0.0f
            }

            txtResultado.text = resultado.toString()
        }


        btnLimpiar.setOnClickListener {
            txtCantidad.setText("")
            txtResultado.setText("Su resultado aqui")
            spnMonedas.setSelection(0)
        }

        btnSalir.setOnClickListener(View.OnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("AppMonedas")
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
    }
}