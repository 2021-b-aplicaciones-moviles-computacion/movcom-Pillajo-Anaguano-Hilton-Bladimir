package archivo

import Medico
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.IOException
import java.io.InputStream

fun escribirEnArchivo(dato: Any) {
    val gsonPretty = GsonBuilder().setPrettyPrinting().create()
    val jsonTutsListPretty: String = gsonPretty.toJson(dato)
    try {
        File("DEBER.txt").printWriter().use { out ->
            out.println(jsonTutsListPretty)
        }
        File("DEBER.json").printWriter().use { out ->
            out.println(jsonTutsListPretty)
        }
    } catch (e: IOException) {
        print(e)
    }
}

fun leerArchivo(): MutableList<Medico> {
    val gson = Gson()
    val inputStream: InputStream = File("DEBER.txt").inputStream()
    val inputString = inputStream.bufferedReader().use { it.readText() }
    val mutableListTutorialType = object : TypeToken<MutableList<Medico>>() {}.type
    return gson.fromJson(inputString, mutableListTutorialType)
}
