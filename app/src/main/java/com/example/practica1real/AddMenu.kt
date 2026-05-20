package com.example.practica1real

    import android.content.Intent
    import android.os.Bundle
    import androidx.activity.enableEdgeToEdge
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
    }
    fun evenClick()
    {
        cardHola.setOnClickListener{
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}