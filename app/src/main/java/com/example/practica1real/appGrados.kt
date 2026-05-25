package com.example.practica1real

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class appGrados : AppCompatActivity() {

    private lateinit var txtCantidad: EditText
    private lateinit var rdbCel: RadioButton
    private lateinit var rdbFah: RadioButton
    private lateinit var txtResultado: TextView
    private lateinit var btnCalcular: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnCerrar: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_app_grados)


        iniciarComponents()
        eventosClic()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun iniciarComponents(){
        txtCantidad = findViewById<EditText>(R.id.txtCantidad)
        rdbCel = findViewById<RadioButton>(R.id.rdbCel)
        rdbFah = findViewById<RadioButton>(R.id.rdbFah)
        txtResultado = findViewById<TextView>(R.id.txtResultado)
        btnCalcular = findViewById<Button>(R.id.btnCalcular)
        btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
        btnCerrar = findViewById<Button>(R.id.btnSalir)
    }

    fun eventosClic() {

        btnCalcular.setOnClickListener {
            // Validar
            if (txtCantidad.text.toString().contentEquals("")){
                Toast.makeText(this, R.string.strMensaje, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var cantidad : Float = txtCantidad.text.toString().toFloat()

            if(rdbCel.isChecked) {

                var celcius: Float = 0.0f
                celcius = (cantidad * 9 / 5) + 32

                txtResultado.text = celcius.toString()
            } else {
                var fah : Float = 0.0f
                fah = (cantidad-32)*5/9

                txtResultado.text = fah.toString()
            }
        }

        btnLimpiar.setOnClickListener {
            txtCantidad.setText("")
            txtResultado.setText("Su resultado aqui:")
            rdbCel.isChecked = true
        }

        btnCerrar.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("AppGrados")
            builder.setMessage("¿Deseas cerrar la aplicación?")
            builder.setPositiveButton("Aceptar") { dialog, which ->
                finish()
            }
            builder.setNegativeButton("Cancelar") { dialog, which ->
                Toast.makeText(applicationContext, "Continuamos en la app",
                    Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }
    }
}