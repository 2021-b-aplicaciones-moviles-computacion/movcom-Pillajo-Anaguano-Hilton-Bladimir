package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    val botonIntent = findViewById<Button>(R.id.btn_intent)
    botonIntent.setOnClicKListener{
        abrirActividadConParametros(CIntentExplicitoParametros::class.java)
    }
    fun abrirActividadConParametros(
        clase: Class<*>
    ){
        val intentoExpicito =  Intent(this, clase)

    }

}