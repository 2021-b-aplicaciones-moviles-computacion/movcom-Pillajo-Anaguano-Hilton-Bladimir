package com.example.medicopaciente

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.widget.Switch
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EditarMedico : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editarmedico)
        val objMedico = intent.getParcelableExtra<BMedico>("medico")

        val id = objMedico!!.id.toString()
        val nombre = objMedico!!.nombre.toString()
        val telefono = objMedico!!.telefono.toString()
        val precioconsulta = objMedico!!.precioconsulta.toString()

        findViewById<EditText>(R.id.tex_ideditarmedico).setText(id)
        findViewById<EditText>(R.id.tex_nombreeditarmedico).setText(nombre)
        findViewById<EditText>(R.id.tex_telefonoeditarmedico).setText(telefono)
        findViewById<EditText>(R.id.text_precioconsultaeditar).setText(precioconsulta)

        val botonEditarMedico = findViewById<Button>(R.id.btn_actualizarmedico)
        botonEditarMedico.setOnClickListener {
            val nuevoMedico = hashMapOf<String, Any>(
                "id" to findViewById<EditText>(R.id.tex_ideditarmedico).text.toString().toInt(),
                "nombre" to findViewById<EditText>(R.id.tex_nombreeditarmedico).text.toString(),
                "telefono" to findViewById<EditText>(R.id.tex_telefonoeditarmedico).text.toString(),
                "precioconsulta" to findViewById<EditText>(R.id.text_precioconsultaeditar).text.toString().toDouble()
            )
            val db = Firebase.firestore
            val referencia =
                db.collection("Medico").document("${id}-${nombre}")
            referencia.update(nuevoMedico)
                .addOnSuccessListener {
                    Toast.makeText(this, "Se actualizo ${nombre}", Toast.LENGTH_SHORT).show()
                    irActividad(Medico::class.java)
                }
        }
    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}