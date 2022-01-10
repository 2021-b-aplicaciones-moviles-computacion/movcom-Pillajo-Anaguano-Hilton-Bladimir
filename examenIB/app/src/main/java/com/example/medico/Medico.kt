package com.example.medico

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog

class Medico : AppCompatActivity() {
    var posicionLista = 0
    var lista = BaseDatosMemoria.arregloMedico
    var adaptador: ArrayAdapter<BMedico>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medico)

        val listV_medico = findViewById<ListView>(R.id.l_medico)
        adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, lista)
        listV_medico.adapter = adaptador
        registerForContextMenu(listV_medico)

        val btnIrCrearMedico = findViewById<Button>(R.id.id_crear_medico)
        btnIrCrearMedico.setOnClickListener { irActividad(CrearMedico::class.java) }

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
        Log.i("context-menu", "Position: ${id}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var objMedico = adaptador!!.getItem(posicionLista)
        return when (item.itemId) {
            R.id.mi_editar_medico -> {
                if (objMedico != null) {
                    irActividadConMedico(EditarMedico::class.java, objMedico)
                    Log.i("medico", "${objMedico}")
                }
                return true
            }
            R.id.mi_eliminar_medico -> {
                AlertDialog.Builder(this).apply {
                    setTitle("Eliminar")
                    setMessage("Estas seguro de Eliminar Medico")
                    setPositiveButton("Si") { _: DialogInterface, _: Int ->
                        if (objMedico != null) {
                            eliminarMedico(objMedico)
                        }
                        adaptador?.notifyDataSetChanged()
                    }
                    setNegativeButton("No", null)
                }.show()
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

    fun eliminarMedico(medico: BMedico) {
        lista.removeIf { medicos -> (medicos == medico) }
        Log.i("eliminar", "${lista}")
    }

}
