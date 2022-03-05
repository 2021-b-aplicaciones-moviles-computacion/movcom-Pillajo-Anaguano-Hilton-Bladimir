package com.example.medicopaciente

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EditarPaciente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editarpaciente)
        val objPaciente = intent.getParcelableExtra<BPaciente>("paciente")

        val id = objPaciente!!.id.toString()
        val nombre = objPaciente!!.nombre.toString()
        val pacientefrecuente = objPaciente!!.pacientefrecuente.toString()
        val edad = objPaciente!!.edad.toString()

        findViewById<EditText>(R.id.txt_idpaciente_editar).setText(id)
        findViewById<EditText>(R.id.txt_nombrepaciente_editar).setText(nombre)
        findViewById<EditText>(R.id.txt_edadpaciente_editar).setText(edad)
        findViewById<Switch>(R.id.pacientefrecuente_editar).setChecked(pacientefrecuente.toBoolean())

        val botonEditarPaciente = findViewById<Button>(R.id.btn_actualizarpaciente)
        botonEditarPaciente.setOnClickListener {
            val nuevoPaciente = hashMapOf<String, Any>(
                "id" to findViewById<EditText>(R.id.txt_idpaciente_editar).text.toString().toInt(),
                "nombre" to findViewById<EditText>(R.id.txt_nombrepaciente_editar).text.toString(),
                "pacientefrecuente" to findViewById<Switch>(R.id.pacientefrecuente_editar).isChecked,
                "edad" to findViewById<EditText>(R.id.txt_edadpaciente_editar).text.toString().toInt(),
                "idMedico" to objPaciente.idMedico.toString().toInt()
            )
            val db = Firebase.firestore
            val referencia = db.collection("Paciente").document("${id}-${nombre}")
            referencia.update(nuevoPaciente).addOnSuccessListener {
                Toast.makeText(this, "Se editó ${nombre}", Toast.LENGTH_SHORT).show()
                irActividad(Medico::class.java)
            }
        }


    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}