package com.example.practica1real

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Toast
import kotlin.math.abs
import android.view.View

class CotizacionActivity : AppCompatActivity() {
    private lateinit var txtCliente: TextView
    private lateinit var txtFolio: TextView
    private lateinit var txtDescripcion: EditText
    private lateinit var txtPrecio: EditText
    private lateinit var txtPorPagI: EditText
    private lateinit var rdb12: RadioButton
    private lateinit var rdb24: RadioButton
    private lateinit var rdb36: RadioButton
    private lateinit var rdb48: RadioButton
    private lateinit var txtPagoInicial: TextView
    private lateinit var txtTotalFin: TextView
    private lateinit var txtPagoMensual: TextView

    private lateinit var btnCalcular: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnCerrar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cotizacion)

        iniciarComponentes()
        eventosClick()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun iniciarComponentes(){
        txtCliente = findViewById<TextView>(R.id.txtCliente)
        txtFolio = findViewById<TextView>(R.id.txtFolio)
        txtDescripcion = findViewById<EditText>(R.id.txtDescripcion)
        txtPrecio = findViewById<EditText>(R.id.txtPrecio)
        txtPorPagI = findViewById<EditText>(R.id.txtPorcentaje)
        txtPagoInicial = findViewById<TextView>(R.id.txtPagoInicial)
        txtPagoMensual = findViewById<TextView>(R.id.txtPagoMensual)
        txtTotalFin = findViewById<TextView>(R.id.txtTotalFin)

        rdb12 = findViewById<RadioButton>(R.id.rdb12)
        rdb24 = findViewById<RadioButton>(R.id.rdb24)
        rdb36 = findViewById<RadioButton>(R.id.rdb36)
        rdb48= findViewById<RadioButton>(R.id.rdb48)

        btnCalcular = findViewById<Button>(R.id.btnCalcular)
        btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
        btnCerrar = findViewById<Button>(R.id.btnCerrar)

        var strCliente: String = intent.getStringExtra("cliente").toString()
        txtCliente.text = strCliente

        var folio: Int = abs(Cotizacion().generarFolio())
        txtFolio.text = "Folio : " + folio.toString()
    }

    fun eventosClick(){

        btnCalcular.setOnClickListener(View.OnClickListener {

            // generar objeto de clase cotizacion
            var cotizacion = Cotizacion()

            // validar
            if (txtDescripcion.text.toString().contentEquals("") ||
                txtPrecio.text.toString().contentEquals("") ||
                txtPorPagI.text.toString().contentEquals("")) {
                Toast.makeText(this, "Falto capturar algun dato", Toast.LENGTH_SHORT).show()

            } else {

                // generar un folio
                txtFolio.text = cotizacion.generarFolio().toString()

                cotizacion.descripcion = txtDescripcion.text.toString()
                cotizacion.precio = txtPrecio.text.toString().toFloat()
                cotizacion.porPagInicial = txtPorPagI.text.toString().toFloat()

                // plazos
                if (rdb12.isChecked) cotizacion.plazos = 12
                if (rdb24.isChecked) cotizacion.plazos = 24
                if (rdb36.isChecked) cotizacion.plazos = 36
                if (rdb48.isChecked) cotizacion.plazos = 48

                // calculos
                txtPagoInicial.text =
                    "Pago Inicial " + ": " + cotizacion.calcularPagoInicial().toString() + ":"

                txtTotalFin.text =
                    "Total a Financiar " + ": " + cotizacion.calcularTotalFin().toString()

                txtPagoMensual.text =
                    "Pago Mensual " + ": " + cotizacion.calcularPagoMensual().toString()
            }
        })

        btnLimpiar.setOnClickListener(View.OnClickListener {

            // limpiar TextView
            txtFolio.text = ""
            txtPagoInicial.text = "Pago Inicial"
            txtTotalFin.text = "Total a Financiar"
            txtPagoMensual.text = "Pago Mensual"

            // limpiar EditText
            txtDescripcion.setText("")
            txtPrecio.setText("")
            txtPorPagI.setText("")

            // Activar el radio12Meses
            rdb12.isChecked = true

        })

        btnCerrar.setOnClickListener(View.OnClickListener {
            finish()
        })
    }
}