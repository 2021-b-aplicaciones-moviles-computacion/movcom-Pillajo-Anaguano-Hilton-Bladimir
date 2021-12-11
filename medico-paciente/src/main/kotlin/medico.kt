import java.io.File
import java.io.InputStream
import java.util.*
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