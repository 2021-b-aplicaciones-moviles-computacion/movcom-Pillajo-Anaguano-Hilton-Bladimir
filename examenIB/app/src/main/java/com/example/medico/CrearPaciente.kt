package com.example.medico

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CrearPaciente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crearpaciente)
        val objMedico = intent.getParcelableExtra<BMedico>("medico")
        Log.i("medico4", "${objMedico}")
        val btnGuardarPaciente = findViewById<Button>(R.id.btn_registrarpaciente)
        btnGuardarPaciente.setOnClickListener {

            if (objMedico != null) {
                val nuevoPaciente = BPaciente(
                    findViewById<EditText>(R.id.txt_idpaciente).text.toString().toInt(),
                    findViewById<EditText>(R.id.txt_nombrepaciente).text.toString(),
                    findViewById<Switch>(R.id.pacientefrecuente).isChecked,
                    findViewById<EditText>(R.id.txt_edadpaciente).text.toString().toInt(),
                    objMedico.id
                )
                BaseDatosMemoria.arregloPaciente.add(nuevoPaciente)
                Toast.makeText(
                    this,
                    "Se creado un nuevo paciente ${findViewById<EditText>(R.id.txt_nombrepaciente).text}",
                    Toast.LENGTH_SHORT
                ).show()
                irActividadConMedico(Paciente::class.java, objMedico)
            }
        }
    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun irActividadConMedico(clase: Class<*>, medico: BMedico) {
        val intent = Intent(this, clase)
        intent.putExtra("medico", medico)
        startActivity(intent)
    }
}