package com.example.medico

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class EditarMedico : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editarmedico)
        val objMedico = intent.getParcelableExtra<BMedico>("medico")

        val id = objMedico!!.id.toString()
        val nombre = objMedico!!.nombre.toString()
        val telefono = objMedico!!.telefono.toString()
        val costoconsulta = objMedico!!.precioconsulta.toString()

        Log.i("medico", "${objMedico}")
        findViewById<EditText>(R.id.tex_ideditarmedico).setText(id)
        findViewById<EditText>(R.id.tex_nombreeditarmedico).setText(nombre)
        findViewById<EditText>(R.id.tex_telefonoeditarmedico).setText(telefono)
        findViewById<EditText>(R.id.text_precioconsultaeditar).setText(costoconsulta)

        val botonEditarMedico = findViewById<Button>(R.id.btn_actualizarmedico)
        botonEditarMedico.setOnClickListener {
            editarMedico(id.toInt())
            Toast.makeText(this, "Se actualizó ${nombre}", Toast.LENGTH_SHORT).show()
            irActividad(Medico::class.java)
        }
    }

    fun editarMedico(id: Int) {
        BaseDatosMemoria.arregloMedico.filter { it.id == id }
            .map {
                it.nombre = findViewById<EditText>(R.id.tex_nombreeditarmedico).text.toString()
                it.telefono = findViewById<EditText>(R.id.tex_telefonoeditarmedico).text.toString()
                it.precioconsulta =
                    findViewById<EditText>(R.id.text_precioconsultaeditar).text.toString()
                        .toDouble()
            }
    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}