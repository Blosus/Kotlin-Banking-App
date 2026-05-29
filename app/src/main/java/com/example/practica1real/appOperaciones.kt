package com.example.practica1real

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
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
        eventosClick()
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
        spnOperaciones = findViewById<Spinner>(R.id.spnOperaciones)
        btnCalcular = findViewById<Button>(R.id.btnCalcular)
        btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
        btnCerrar = findViewById<Button>(R.id.btnCerrar)
        image = findViewById<ImageView>(R.id.image)

        val items = resources.getStringArray(R.array.operaciones)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        spnOperaciones.adapter = adapter
    }

    fun eventosClick(){

        spnOperaciones.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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

        btnCalcular.setOnClickListener {
            if (txtNumero1.text.toString().contentEquals("") ||
                txtNumero1.text.toString().contentEquals("") ||
                txtNumero2.text.toString().toFloat() == 0.0f ||
                txtNumero2.text.toString().toFloat() == 0.0f ) {
                Toast.makeText(this, R.string.strMensaje2, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var numero1 = txtNumero1.text.toString().toFloat()
            var numero2 = txtNumero2.text.toString().toFloat()

            val resultado = when(pos){
                0 -> numero1+numero2
                1 -> numero1-numero2
                2 -> numero1*numero2
                3 -> numero1/numero2
                else -> 0.0f
            }

            when(pos){
                0 -> image.setImageResource(R.mipmap.suma)
                1 -> image.setImageResource(R.mipmap.resta)
                2 -> image.setImageResource(R.mipmap.multiplicar)
                3 -> image.setImageResource(R.mipmap.dividir)
                else -> image.setImageResource(R.mipmap.operaciones)
            }

            txtResultado.text = String.format("%.2f", resultado)
        }

        btnLimpiar.setOnClickListener {
            spnOperaciones.setSelection(0)
            txtNumero1.setText("")
            txtNumero2.setText("")
            txtResultado.setText(R.string.strResultado)
            image.setImageResource(R.mipmap.operaciones)
        }

        btnCerrar.setOnClickListener(View.OnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("appOperaciones")
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