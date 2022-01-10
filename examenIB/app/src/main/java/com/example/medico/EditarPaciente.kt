package com.example.medico

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class EditarPaciente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editarpaciente)
        val objPaciente = intent.getParcelableExtra<BPaciente>("paciente")

        val id = objPaciente!!.id.toString()
        val nombre = objPaciente!!.nombre.toString()
        val pacientefrecuente = objPaciente!!.pacientefrecuente.toString()
        val edad = objPaciente!!.edad.toString()
        val objMedico = BaseDatosMemoria.arregloMedico.filter { it.id == objPaciente.idMedico }
        Log.i("paciente", "${objPaciente} ${objMedico[0]}")

        findViewById<EditText>(R.id.txt_idpaciente_editar).setText(id)
        findViewById<EditText>(R.id.txt_nombrepaciente_editar).setText(nombre)
        findViewById<EditText>(R.id.txt_edadpaciente_editar).setText(edad)
        findViewById<Switch>(R.id.pacientefrecuente_editar).setChecked(pacientefrecuente.toBoolean())

        val botonEditarModelo = findViewById<Button>(R.id.btn_actualizarpaciente)
        botonEditarModelo.setOnClickListener {
            editarModelo(id.toInt())
            Log.i("paciente", "${objMedico[0]}")
            Toast.makeText(this, "Se editó datos del paciente${nombre}", Toast.LENGTH_SHORT).show()
            irActividadConMarca(Paciente::class.java, objMedico[0])

        }


    }

    fun editarModelo(id: Int) {
        BaseDatosMemoria.arregloPaciente.filter { it.id == id }
            .map {
                it.nombre = findViewById<EditText>(R.id.txt_nombrepaciente_editar).text.toString()
                it.edad = findViewById<EditText>(R.id.txt_edadpaciente_editar).text.toString().toInt()
                it.pacientefrecuente = findViewById<Switch>(R.id.pacientefrecuente_editar).isChecked
            }
    }

    fun irActividadConMarca(clase: Class<*>, medico: BMedico) {
        val intent = Intent(this, clase)
        intent.putExtra("medico", medico)
        startActivity(intent)
    }
}