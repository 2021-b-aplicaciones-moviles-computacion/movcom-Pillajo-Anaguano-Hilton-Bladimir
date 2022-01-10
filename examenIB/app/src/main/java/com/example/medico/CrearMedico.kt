package com.example.medico

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class CrearMedico : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crearmedico)
        val btnGuardarMedico = findViewById<Button>(R.id.btn_registrarmedico)

        btnGuardarMedico.setOnClickListener {
            val nuevoMedico = BMedico(
                findViewById<EditText>(R.id.txt_idmedico).text.toString().toInt(),
                findViewById<EditText>(R.id.txt_nombremedico).text.toString(),
                findViewById<EditText>(R.id.txt_telefonomedico).text.toString(),
                findViewById<EditText>(R.id.txt_consultamedico).text.toString().toDouble()
            )
            BaseDatosMemoria.arregloMedico.add(nuevoMedico)
            Log.i("Medico", "${BaseDatosMemoria.arregloMedico}")
            Toast.makeText(
                this,
                "Se a creado ${findViewById<EditText>(R.id.txt_nombremedico).text}",
                Toast.LENGTH_SHORT
            ).show()
            startActivity(
                Intent(
                    this,
                    Medico::class.java
                )
            )
        }
    }
}