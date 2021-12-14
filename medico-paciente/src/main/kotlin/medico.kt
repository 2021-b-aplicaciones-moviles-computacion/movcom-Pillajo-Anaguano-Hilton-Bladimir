import java.util.*
import java.io.File
import java.io.InputStream
import javax.swing.JOptionPane

class Medico(
    var ruc: String,
    var nombre: String,
    var telefono: Int,
    var fechanacimiento: String,
    var precioconsulta: Double,
    var pacientes: MutableList<Paciente>? = null
) {

}

fun crearMedico(
    ruc: String,
    nombre: String,
    telefono: Int,
    fechanacimiento: String,
    precioconsulta: Double,
): MutableList<Medico> {
    val pacientes = mutableListOf<Paciente>()
    return mutableListOf(Medico(ruc, nombre,telefono, fechanacimiento, precioconsulta, pacientes = pacientes))
}
fun eliminarMedico(
    nombre: String,
    medicos: MutableList<Medico>
): MutableList<Medico> {
    val ind = RetornarIndice(nombre, medicos)
    val existeEmpresa = ind > -1
    if (existeEmpresa) {
        medicos.removeAt(ind)
    }
    return medicos
}
fun editarMedico(
    nombre: String,
    datoEditar: String,
    nuevoValor: String,
    medicos: MutableList<Medico>
): MutableList<Medico> {
    val ind = RetornarIndice(nombre, medicos)
    val existeMedico = ind > -1
    if (existeMedico) {
        when (datoEditar) {
            "nombre" -> {
                medicos[ind].nombre = nuevoValor
            }
            "ruc" -> {
                medicos[ind].ruc = nuevoValor
            }
            "telefono" -> {
                medicos[ind].telefono = nuevoValor.toInt()
            }
            else -> {
                JOptionPane.showMessageDialog(null, "No se encontro el Medico  ${datoEditar}")
            }
        }
    }
    return medicos
}

fun buscarMedico(
    parametro: String,
    datoConsulta: String,
    medicos: MutableList<Medico>
): List<Medico> {
    var medicoEncontrado: List<Medico> = emptyList()
    when (parametro) {
        "nombre" -> {
            medicoEncontrado = medicos
                .filter { medico: Medico ->
                    return@filter medico.nombre == datoConsulta
                }
        }
        else -> {
            JOptionPane.showMessageDialog(null,"Busqueda de  ${parametro} no encontrado")
        }
    }
    return medicoEncontrado
}

fun RetornarIndice(
    nombre: String,
    medicos: MutableList<Medico>
): Int {
    val respuesta = medicos.filter { medico: Medico ->
        return@filter medico.nombre == nombre
    }
    val existeMedico = respuesta.size > 0
    if (!existeMedico) {
        JOptionPane.showMessageDialog(null, "No se encontro el Medico  ${nombre}")
        return -1
    }
    return medicos.indexOf(respuesta[0])
}

fun listarInformacion(): String {
    val inputStream: InputStream = File("BaseDatos.txt").inputStream()
    val inputString = inputStream.bufferedReader().use { it.readText() }
    return inputString
}