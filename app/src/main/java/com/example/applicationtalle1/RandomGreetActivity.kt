package com.example.applicationtalle1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class RandomGreetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_greet)
        val saludo = findViewById<TextView>(R.id.saludo)
        val idioma = intent.getStringExtra("idioma")
        val tipos = ArrayList<Pair<String, String>>()
        tipos.add(Pair("Ingles","Hello"))
        tipos.add(Pair("Coreano","annyeonghaseyo"))
        tipos.add(Pair("Frances","Bonjour"))
        tipos.add(Pair("Español","Hola parcero"))
        tipos.add(Pair("Italiano","Ciao"))
        var saludar = tipos.find { pair -> pair.first == idioma };
        if (saludar != null) {
            saludo.text=saludar.second
        }
    }
}