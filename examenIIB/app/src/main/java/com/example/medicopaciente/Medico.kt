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

class Medico : AppCompatActivity() {
    var posicionLista = 0
    var listaMedico = arrayListOf<BMedico>()
    var adaptador: ArrayAdapter<BMedico>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medico)

        val btnIrCrearMedico = findViewById<Button>(R.id.id_crear_medico)
        btnIrCrearMedico.setOnClickListener { irActividad(CrearMedico::class.java) }

        val db = Firebase.firestore
        var documentoMedico: (MutableList<DocumentSnapshot>)
        val medicos = db.collection("Medico").orderBy("id")
        medicos.get().addOnSuccessListener {
            documentoMedico = it.documents
            documentoMedico.forEach { iteracion ->
                listaMedico.add(
                    BMedico(
                        iteracion.get("id").toString().toInt(),
                        iteracion.get("nombre").toString(),
                        iteracion.get("telefono").toString(),
                        iteracion.get("precioconsulta").toString().toDouble()
                    )
                )
            }
            if (listaMedico.size > 0) {
                val list_medico = findViewById<ListView>(R.id.l_medico)
                adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaMedico)
                list_medico.adapter = adaptador
                registerForContextMenu(list_medico)
                adaptador!!.notifyDataSetChanged()
            }
        }.addOnFailureListener { }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_medico, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        posicionLista = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var objMedico = adaptador!!.getItem(posicionLista)
        return when (item.itemId) {
            R.id.mi_editar_medico -> {
                if (objMedico != null) {
                    irActividadConMedico(EditarMedico::class.java, objMedico)
                }
                return true
            }
            R.id.mi_verPacientes -> {
                if (objMedico != null) {
                    irActividadConMedico(Paciente::class.java, objMedico)
                    Log.i("medico1", "${objMedico}")
                }
                return true
            }
            else -> {
                super.onContextItemSelected(item)
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