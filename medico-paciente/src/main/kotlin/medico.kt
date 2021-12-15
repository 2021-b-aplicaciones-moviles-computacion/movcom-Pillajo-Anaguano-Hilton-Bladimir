import java.io.File
import java.io.InputStream
import javax.swing.JOptionPane

class Medico(
    var ruc: String,
    var nombre: String,
    var telefono: Int,
    var fechanacimiento: String,
    var consulta: Double,
    var pacientes: MutableList<Paciente>? = null
) {
}

fun buscarMedico(
    parametro: String,
    datoConsulta: String,
    medicos: MutableList<Medico>
): List<Medico> {
    var medicosEncontrados: List<Medico> = emptyList()
    when (parametro) {
        "nombre" -> {
            medicosEncontrados = medicos
                .filter { medico: Medico ->
                    return@filter medico.nombre == datoConsulta
                }
        }
        "ruc" -> {
            medicosEncontrados = medicos
                .filter { medico: Medico ->
                    return@filter medico.ruc == datoConsulta
                }
        }
        else -> {
            JOptionPane.showMessageDialog(null,"Parametro de busqueda  ${parametro} no encontrado")
//            println("Campo ${campo} no encontrado")
        }
    }
    return medicosEncontrados
}

fun editarMedico(
    nombre: String,
    datoEditar: String,
    nuevoValor: String,
    medicos: MutableList<Medico>
): MutableList<Medico> {
    val indice = buscarYRetornarIndice(nombre, medicos)
    val existeMedico = indice > -1
    if (existeMedico) {
        when (datoEditar) {
            "nombre" -> {
                medicos[indice].nombre = nuevoValor
            }
            "ruc" -> {
                medicos[indice].ruc = nuevoValor
            }
            "telefono" -> {
                medicos[indice].telefono = nuevoValor.toInt()
            }
            else -> {
                JOptionPane.showMessageDialog(null, "No se encontro el medico  ${datoEditar}")
            }
        }
    }
    return medicos
}

fun crearMedico(
    ruc: String,
    nombre: String,
    telefono: Int,
    fechanacimiento: String,
    consulta: Double,
): MutableList<Medico> {
    val pacientes = mutableListOf<Paciente>()
    return mutableListOf(Medico(ruc, nombre,telefono, fechanacimiento,consulta, pacientes=pacientes))
}

fun eliminarMedico(
    nombre: String,
    medicos: MutableList<Medico>
): MutableList<Medico> {
    val indice = buscarYRetornarIndice(nombre, medicos)
    val existeMedico = indice > -1
    if (existeMedico) {
        medicos.removeAt(indice)
    }
    return medicos
}

fun buscarYRetornarIndice(nombre: String, medicos: MutableList<Medico>): Int {
    val respuesta = medicos.filter { medico: Medico ->
        return@filter medico.nombre == nombre
    }
    val existeEmpresa = respuesta.size > 0
    if (!existeEmpresa) {
        JOptionPane.showMessageDialog(null, "No se encontro el medico ${nombre}")
        return -1
    }
    return medicos.indexOf(respuesta[0])

}

fun listarInformacion(): String {
    val inputStream: InputStream = File("DEBER.txt").inputStream()
    val inputString = inputStream.bufferedReader().use { it.readText() }
    return inputString
}
