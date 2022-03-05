package com.example.medicopaciente

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearMedico : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crearmedico)
        val btnGuardarMedico = findViewById<Button>(R.id.btn_registrarmedico)

        btnGuardarMedico.setOnClickListener {
            val textIdMedico = findViewById<EditText>(R.id.txt_idmedico)
            val textNombremedico = findViewById<EditText>(R.id.txt_nombremedico)
            val textTelefonomedico = findViewById<EditText>(R.id.txt_telefonomedico)
            val textConsultamedico = findViewById<EditText>(R.id.txt_consultamedico)
            val nuevoMedico = hashMapOf<String, Any>(
                "id" to textIdMedico.text.toString().toInt(),
                "nombre" to textNombremedico.text.toString(),
                "telefono" to textTelefonomedico.text.toString(),
                "precioconsulta" to textConsultamedico.text.toString().toDouble()
            )
            val db = Firebase.firestore
            val referencia = db.collection("Medico").document("${textIdMedico.text}-${textNombremedico.text}")
            referencia
                .set(nuevoMedico)
                .addOnSuccessListener {
                    Toast.makeText(
                        this,
                        "Se ha creado ${findViewById<EditText>(R.id.txt_nombremedico).text}",
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
}