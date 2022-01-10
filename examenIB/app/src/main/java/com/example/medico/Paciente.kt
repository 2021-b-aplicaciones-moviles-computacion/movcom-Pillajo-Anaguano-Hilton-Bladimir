package com.example.medico

import android.content.DialogInterface
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
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class Paciente : AppCompatActivity() {
    var posicionLista = 0
    var lista = BaseDatosMemoria.arregloPaciente
    var adaptador: ArrayAdapter<BPaciente>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paciente)

        val objMedico = intent.getParcelableExtra<BMedico>("medico")
        Log.i("medico2", "${objMedico}")
        val listV_pacientes = findViewById<ListView>(R.id.l_pacientes)

        if (objMedico != null) {
            adaptador = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                lista.filter { it.idMedico == objMedico.id })
        }
        listV_pacientes.adapter = adaptador
        registerForContextMenu(listV_pacientes)
        adaptador!!.notifyDataSetChanged()

        val btnCrearPaciente = findViewById<Button>(R.id.btn_crearpaciente)
        btnCrearPaciente.setOnClickListener {
            if (objMedico != null) {
                irActividadConMedico(CrearPaciente::class.java, objMedico)
                Log.i("medico3", "${objMedico}")
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
                    Log.i("modelo", "${objPaciente}")
                }
                return true
            }
            R.id.mi_eliminar_paciente -> {
                AlertDialog.Builder(this).apply {
                    setTitle("Eliminar")
                    setMessage("Estas seguro de Eliminar Paciente")
                    setPositiveButton("Si") { _: DialogInterface, _: Int ->
                        if (objPaciente != null) {
                            eliminarpaciente(objPaciente)
                            adaptador!!.remove(adaptador!!.getItem(posicionLista))
                        }
                        adaptador?.notifyDataSetChanged()
                    }
                    setNegativeButton("No", null)
                }.show()
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

    fun eliminarpaciente(modelo: BPaciente) {
        lista.removeIf { modelos -> (modelos == modelo) }
    }
}