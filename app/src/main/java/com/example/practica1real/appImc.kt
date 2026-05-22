package com.example.practica1real

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class appImc : AppCompatActivity() {

    private lateinit var btnLimpiar: Button
    private lateinit var btnCerrar: Button
    private lateinit var btnCalcular: Button
    private lateinit var txtAltura: EditText
    private lateinit var txtPeso: EditText
    private lateinit var txtResultado: TextView
    private lateinit var imgPeso: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_app_imc)
        iniciarComponentes()
        eventosClick()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun iniciarComponentes(){
        btnLimpiar=findViewById<Button>(R.id.btnLimpiar);
        btnCalcular=findViewById<Button>(R.id.btnCalcular);
        btnCerrar=findViewById<Button>(R.id.btnCerrar);
        txtAltura=findViewById<EditText>(R.id.txtAltura);
        txtPeso=findViewById<EditText>(R.id.txtPeso);
        txtResultado=findViewById<TextView>(R.id.txtResultado);
        imgPeso=findViewById<ImageView>(R.id.image)
    }

    fun eventosClick(){
        btnCalcular.setOnClickListener {
            if (txtAltura.text.toString().contentEquals("") ||
                txtPeso.text.toString().contentEquals("") ||
                txtPeso.text.toString().toFloat() <= 0 ||
                txtAltura.text.toString().toFloat() <= 0
            ) {
                Toast.makeText(
                    applicationContext,
                    "Falto capturar la altura o el peso",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            var altura: Float = txtAltura.text.toString().toFloat()
            var peso: Float = txtPeso.text.toString().toFloat()
            var imc: Float = 0.0f
            imc = peso / (altura * altura)
            txtResultado.text = imc.toString();

            if (imc < 18.5) imgPeso.setImageResource(R.mipmap.bajopeso)
            if (imc >= 18.5 && imc < 24.9) imgPeso.setImageResource(R.mipmap.pesonormal)
            if (imc >= 25 && imc < 29.9) imgPeso.setImageResource(R.mipmap.sobrepeso)
            if (imc >= 30) imgPeso.setImageResource(R.mipmap.obesidad)

        }
                btnLimpiar.setOnClickListener {
                    imgPeso.setImageResource(R.mipmap.categorias)
                    txtAltura.setText("")
                    txtPeso.setText("")
                    txtResultado.setText("")
        }

        btnCerrar.setOnClickListener(View.OnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("AppImc")
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