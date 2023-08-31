package com.example.applicationtalle1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonTriqui:Button = findViewById(R.id.buttonTriqui)
        val buttonRandom:Button = findViewById(R.id.buttonRandom)
        val buttonCountries:Button = findViewById(R.id.buttonCountries)
        val spinner:Spinner = findViewById(R.id.spinner)

        val intent = Intent( this, TriquiActivity::class.java)
        val intent2 = Intent (this, RandomGreetActivity::class.java)


        buttonTriqui.setOnClickListener(){
            startActivity(intent)
        }

        buttonRandom.setOnClickListener(){
            val idioma = spinner.selectedItem.toString()
            intent2.putExtra("idioma",idioma)
            startActivity(intent2)

        }




        buttonCountries.setOnClickListener(){
            val intent3 = Intent (this, CountriesActivity::class.java)
            startActivity(intent3)
        }

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val selectedItem = resources.getStringArray(R.array.spinner)[p2]
    }


    override fun onNothingSelected(p0: AdapterView<*>?) {
    }


}