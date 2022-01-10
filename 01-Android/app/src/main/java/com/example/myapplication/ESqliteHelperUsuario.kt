package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class ESqliteHelperUsuario (
    contexto: Context?
):SQLiteOpenHelper(
    contexto,
    "moviles",
    null,
    1
){
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptCrearTablaUsuario = 
            """
                CREATE TABLE USUARIO(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR(50),
                descripcion varchar(50)
                ) 
            """.trimIndent()
        Log.i("bdd","Creando la tabla de usuario")
        db?.execSQL(scriptCrearTablaUsuario)
    }
    fun crearUsuarioFormulario(
        nombre: String,
        descripcion: String
    ):Boolean{
        val baseDatosEscritura=writableDatabase
        val valoresaGuardar=ContentValues()
        valoresaGuardar.put("nombre",nombre)
        valoresaGuardar.put("descripcion",descripcion)
        val resultadoEscritura: Long = baseDatosEscritura
            .insert(
              "USUARIO",
              null,
              valoresaGuardar
            )
        baseDatosEscritura.close()
        return  if (resultadoEscritura.toInt()==-1) false else true
    }
    fun consultarUsuarioPorId{}
    fun eliminarUsuarioFormulario{}
    fun actualizarUsuarioFormulario{}
    
    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int){}
}


