package com.example.medicopaciente

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearPaciente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crearpaciente)
        val objMedico = intent.getParcelableExtra<BMedico>("medico")
        val btnGuardarPaciente = findViewById<Button>(R.id.btn_registrarpaciente)
        btnGuardarPaciente.setOnClickListener {

            if (objMedico != null) {
                val textIdpaciente = findViewById<EditText>(R.id.txt_idpaciente)
                val textNombrepaciente = findViewById<EditText>(R.id.txt_nombrepaciente)
                val textPacientefrecuente = findViewById<Switch>(R.id.pacientefrecuente)
                val textEdadpaciente = findViewById<EditText>(R.id.txt_edadpaciente)
                val nuevoPaciente = hashMapOf<String, Any>(
                    "id" to textIdpaciente.text.toString().toInt(),
                    "nombre" to textNombrepaciente.text.toString(),
                    "pacientefrecuente" to textPacientefrecuente.isChecked,
                    "edad" to textEdadpaciente.text.toString().toInt(),
                    "idMedico" to objMedico.id
                )

                val db = Firebase.firestore
                var referencia =
                    db.collection("Paciente").document("${textIdpaciente.text}-${textNombrepaciente.text}")
                referencia.set(nuevoPaciente).addOnSuccessListener {
                    Toast.makeText(
                        this,
                        "Se creó ${findViewById<EditText>(R.id.txt_nombrepaciente).text}",
                        Toast.LENGTH_SHORT
                    ).show()
                    irActividadConMedico(Paciente::class.java, objMedico)
                }
            }
        }
    }


    fun irActividadConMedico(clase: Class<*>, medico: BMedico) {
        val intent = Intent(this, clase)
        intent.putExtra("medico", medico)
        startActivity(intent)
    }
}