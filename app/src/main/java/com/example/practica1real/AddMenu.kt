package com.example.practica1real

    import android.content.Intent
    import android.os.Bundle
    import android.view.View
    import android.widget.Toast
    import androidx.activity.enableEdgeToEdge
    import androidx.appcompat.app.AlertDialog
    import androidx.appcompat.app.AppCompatActivity
    import androidx.cardview.widget.CardView
    import androidx.core.view.ViewCompat
    import androidx.core.view.WindowInsetsCompat

class AddMenu : AppCompatActivity()
{
    private lateinit var cardHola: CardView
    private lateinit var cardIMC: CardView
    private lateinit var cardMoneda: CardView
    private lateinit var cardGrados: CardView
    private lateinit var cardCotizar: CardView
    private lateinit var cardSpinner: CardView
    private lateinit var cardSalir: CardView
    private lateinit var cardPreExamen1 : CardView
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.addmenu)
        inciarComp()
        evenClick()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main))
        { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun inciarComp()
    {
        cardHola=findViewById<CardView>(R.id.cardHola)
        cardIMC=findViewById<CardView>(R.id.cardIMC)
        cardMoneda=findViewById<CardView>(R.id.cardDivisa)
        cardGrados=findViewById<CardView>(R.id.cardGrados)
        cardSpinner=findViewById<CardView>(R.id.cardSpinner)
        cardCotizar=findViewById<CardView>(R.id.cardToyota)
        cardSalir=findViewById<CardView>(R.id.cardSalir)
        cardPreExamen1 = findViewById<CardView>(R.id.cardPreEx1)
    }
    fun evenClick()
    {
        cardHola.setOnClickListener{
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        cardIMC.setOnClickListener {
            val intent = Intent(this, appImc::class.java)
            startActivity(intent)
        }

        cardGrados.setOnClickListener {
            val intent = Intent(this, appGrados::class.java)
            startActivity(intent)
        }

        cardGrados.setOnClickListener {
            val intent = Intent(this, appGrados::class.java)
            startActivity(intent)
        }

        cardMoneda.setOnClickListener {
            val intent = Intent(this, appMonedas::class.java)
            startActivity(intent)
        }

        cardPreExamen1.setOnClickListener {
            val intent = Intent(this, appOperaciones::class.java)
            startActivity(intent)
        }

        cardSalir.setOnClickListener {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("AppMenu")
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
        }
    }
}