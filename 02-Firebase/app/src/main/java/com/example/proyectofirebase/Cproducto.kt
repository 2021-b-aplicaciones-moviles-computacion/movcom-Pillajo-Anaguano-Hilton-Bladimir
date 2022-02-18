package com.example.proyectofirebase

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.firestore

class Cproducto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cproducto)

        val botonCrear = findViewById<Button>(R.id.btn_crear_producto)
        botonCrear
            .setOnClickListener{
                crearProducto()
            }
    }

    @SuppressLint("WrongViewCast")
    fun crearProducto(){
        val editTextNombre = findViewById<EditText>(R.id.et_nombre_producto)
        val editTextPrecio = findViewById<EditText>(R.id.et_precio_producto)
        val nuevoProducto = hashMapOf<String, Any>(
            "nombre" to editTextNombre.text.toString(),
            "precio" to editTextPrecio.text.toString().toDouble()
        )
        val db = Firebase.firestore
        val referencia = db.collection("producto")

        referencia
            .add(nuevoProducto)
            .addOnSuccessListener{
                editTextNombre.text.clear()
                editTextPrecio.text.clear()
            }
            .addOnFailureListener{}
    }
}