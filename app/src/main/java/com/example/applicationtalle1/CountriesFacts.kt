package com.example.applicationtalle1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.json.JSONObject
import java.io.InputStream

class CountriesFacts : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countries_facts)
        val json = loadJSONFromAsset("paises.json")
        InfoPais(json)

    }
    fun loadJSONFromAsset(fileName: String): String {
        val inputStream: InputStream = assets.open(fileName)
        val buffer = ByteArray(inputStream.available())
        inputStream.read(buffer)
        inputStream.close()
        return String(buffer)
    }
    fun InfoPais(jsonString: String) {

        val pais = intent.getStringExtra("paisElegido")
        val jsonObject = JSONObject(jsonString)
        val jsonArray = jsonObject.getJSONArray("paises")

        val textViewName: TextView = findViewById(R.id.nombre)
        val textViewInt: TextView = findViewById(R.id.nombreInter)
        val textViewSigla: TextView = findViewById(R.id.siglas)
        val textViewCapital: TextView = findViewById(R.id.capital)

        val countryDataMap = mutableMapOf<String, List<String>>()

        for (i in 0 until jsonArray.length()) {

            val jsonObject: JSONObject = jsonArray.getJSONObject(i)
            val countryName = jsonObject.getString("nombre_pais")
            val internName = jsonObject.getString("nombre_pais_int")
            val sigla = jsonObject.getString("sigla")
            val capital = jsonObject.getString("capital")
            countryDataMap[countryName] = listOf(internName, sigla, capital)
        }

        val key: String? = pais
        val tail = countryDataMap[key]

        if (tail != null) {
            val internName = tail[0]
            val sigla = tail[1]
            val capital = tail[2]

            textViewName.text = pais
            textViewInt.text = internName
            textViewSigla.text = sigla
            textViewCapital.text = capital
        }
    }
}