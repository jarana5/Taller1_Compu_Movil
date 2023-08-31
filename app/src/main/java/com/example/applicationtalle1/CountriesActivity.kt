package com.example.applicationtalle1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

class CountriesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countries)
        val json = JSONObject(loadJASONFromAsset())
        val paises=json.getJSONArray("paises")
        var nombre=ArrayList<String>()
        val lista = findViewById<ListView>(R.id.lista_paises)
        for (i in 0  until  paises.length()){
            val jsonObject = paises.getJSONObject(i)
            val nombrePais = jsonObject.getString("nombre_pais")
           nombre.add(nombrePais)
        }
        val adapter1= ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,nombre)
        lista.adapter=adapter1
        lista.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            val paisElegido = parent.getItemAtPosition(position) as String
            val intent = Intent(this, CountriesFacts::class.java)
            intent.putExtra("paisElegido", paisElegido)
            startActivity(intent)
        }


    }

    fun loadJASONFromAsset(): String?{
        var json: String? = null
        try{
            val istream: InputStream = assets.open("paises.json")
            val size:Int = istream.available()
            val buffer = ByteArray(size)
            istream.read(buffer)
            istream.close()
            json = buffer.toString(Charsets.UTF_8)
        }catch (ex: IOException){
            ex.printStackTrace()
            return null
        }
        return json
    }
}