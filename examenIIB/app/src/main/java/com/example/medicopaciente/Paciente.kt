package com.example.medicopaciente

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Paciente : AppCompatActivity() {
    var posicionLista = 0
    var listaPaciente = arrayListOf<BPaciente>()
    var adaptador: ArrayAdapter<BPaciente>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paciente)

        val objMedico = intent.getParcelableExtra<BMedico>("medico")
        val db = Firebase.firestore
        var documentoPaciente: (MutableList<DocumentSnapshot>)
        val pacientes = db.collection("Paciente").orderBy("id")
        pacientes.get().addOnSuccessListener {
            documentoPaciente = it.documents
            documentoPaciente.forEach { iteracion ->
                listaPaciente.add(
                    BPaciente(
                        iteracion.get("id").toString().toInt(),
                        iteracion.get("nombre").toString(),
                        iteracion.get("pacientefrecuente").toString().toBoolean(),
                        iteracion.get("edad").toString().toInt(),
                        iteracion.get("idMedico").toString().toInt()
                    )
                )
                Log.i("medico", "${listaPaciente}")
            }
            if (listaPaciente.size > 0) {
                val list_pacientes = findViewById<ListView>(R.id.l_pacientes)
                adaptador = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    listaPaciente
                )
                list_pacientes.adapter = adaptador
                registerForContextMenu(list_pacientes)
                adaptador!!.notifyDataSetChanged()
            }
        }

        val btnIrCrearPaciente = findViewById<Button>(R.id.btn_crearpaciente)
        btnIrCrearPaciente.setOnClickListener {
            if (objMedico != null) {
                irActividadConMedico(CrearPaciente::class.java, objMedico)
            }
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_paciente, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        posicionLista = id
        Log.i("context-menu", "Position: ${id}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var objPaciente = adaptador!!.getItem(posicionLista)
        return when (item.itemId) {
            R.id.mi_editar_paciente -> {
                if (objPaciente != null) {
                    irActividadConPaciente(EditarPaciente::class.java, objPaciente)
                    Log.i("paciente", "${objPaciente}")
                }
                return true
            }
            else -> {
                super.onContextItemSelected(item)
            }
        }
    }

    fun irActividadConMedico(clase: Class<*>, medico: BMedico) {
        val intent = Intent(this, clase)
        intent.putExtra("medico", medico)
        startActivity(intent)
    }

    fun irActividadConPaciente(clase: Class<*>, paciente: BPaciente) {
        val intent = Intent(this, clase)
        intent.putExtra("paciente", paciente)
        startActivity(intent)
    }

}