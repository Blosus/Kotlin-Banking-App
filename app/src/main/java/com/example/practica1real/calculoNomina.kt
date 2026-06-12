package com.example.practica1real

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
class calculoNomina : AppCompatActivity() {

    // Lateinit variables for UI components
    private lateinit var lblNumRecibo: TextView
    private lateinit var lblNombre: TextView
    private lateinit var txtHorasNormal: EditText
    private lateinit var txtHorasExtra: EditText
    private lateinit var rdgPuesto: RadioGroup
    private lateinit var lblSubtotal: TextView
    private lateinit var lblImpuesto: TextView
    private lateinit var lblTotal: TextView
    private lateinit var btnCalcular: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnRegresar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculo_nomina)

        iniciarComponentes()
        val nombre = intent.getStringExtra("nombre")
        val cotizacion = Cotizacion();
        val numFolio = cotizacion.generarFolio().toString()

        lblNumRecibo.text = numFolio
        lblNombre.text = nombre

        btnRegresar.setOnClickListener(View.OnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("calculoNomina")
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
        btnCalcular.setOnClickListener {
            Calcular()
        }

        btnLimpiar.setOnClickListener {
            Limpiar()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun iniciarComponentes(){
        lblNumRecibo = findViewById(R.id.lblNumRecibo)
        lblNombre = findViewById(R.id.lblNombre)
        txtHorasNormal = findViewById(R.id.txtHorasNormal)
        txtHorasExtra = findViewById(R.id.txtHorasExtra)
        rdgPuesto = findViewById(R.id.rdgPuesto)
        lblSubtotal = findViewById(R.id.lblSubtotal)
        lblImpuesto = findViewById(R.id.lblImpuesto)
        lblTotal = findViewById(R.id.lblTotal)
        btnCalcular = findViewById(R.id.btnCalcular)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnRegresar = findViewById(R.id.btnRegresar)
    }

    private fun Calcular(){
        if(txtHorasNormal.text.isEmpty() || txtHorasExtra.text.isEmpty() ){
            makeText(applicationContext , "Falta Ingresar Datos", Toast.LENGTH_SHORT).show()
            return
        }
        if(txtHorasExtra.text.toString().any { it.isLetter() } || txtHorasExtra.text.toString().toIntOrNull() == null){
            makeText(applicationContext , "Horas Extra no es un dato numerico entero", Toast.LENGTH_SHORT).show()
            return
        }
        if(txtHorasNormal.text.toString().any { it.isLetter() }|| txtHorasNormal.text.toString().toIntOrNull() == null){
            makeText(applicationContext , "Horas Trabajadas no es un dato numerico entero", Toast.LENGTH_SHORT).show()
            return
        }


        var sueldoBase = 200.0
        val horasTrabajadas = txtHorasNormal.text.toString().toInt()
        val horasExtra = txtHorasExtra.text.toString().toInt()

        val checkedId = rdgPuesto.checkedRadioButtonId
        val radioButton = findViewById<RadioButton>(checkedId)
        val index = rdgPuesto.indexOfChild(radioButton)

        if(index == 0){
            sueldoBase*=1.2
        }
        else if (index == 1){
            sueldoBase*=1.5
        }
        else{
            sueldoBase*=2
        }

        val subTotal = (sueldoBase * horasTrabajadas) + (horasExtra*sueldoBase*2)
        lblSubtotal.text = "$$subTotal"
        val impuesto = subTotal * .16
        lblImpuesto.text = "$$impuesto"
        val  totalAPagar = subTotal - impuesto
        lblTotal.text = "$$totalAPagar"
    }

    private fun Limpiar(){
        lblTotal.text = ""
        lblSubtotal.text=""
        lblImpuesto.text=""
        txtHorasNormal.text.clear()
        txtHorasExtra.text.clear()
        val firstRadioButton = rdgPuesto.getChildAt(0) as RadioButton
        firstRadioButton.isChecked = true
    }
}
