import javax.swing.JOptionPane

class Paciente(
    var cedula: String,
    var nombre: String,
    var telefono: String,
    var edad: Int,
    var pacientefrecuente: Boolean = true
) {

}
fun crearPaciente(
    nombreMedico: String,
    paciente: Paciente,
    medicos: MutableList<Medico>
): MutableList<Medico> {
    val indMedico = RetornarIndice(nombreMedico, medicos)
    val existeMedico = indMedico > -1
    if (existeMedico) {
        medicos[indMedico].pacientes?.add(paciente)
    }
    return medicos
}

fun eliminarPaciente(
    cedula: String,
    medicos: MutableList<Medico>
): MutableList<Medico> {
    val ind = RetornarIndices(cedula, medicos)
    val existeMedico = ind["medico"]!! > -1
    val existePaciente = ind["paciente"]!! > -1

    if (existeMedico && existePaciente) {
        val indMedico = ind["medico"] as Int
        val indPaciente = ind["paciente"] as Int
        medicos[indMedico].pacientes?.removeAt(indPaciente)
    }
    return medicos
}

fun editarPaciente(
    cedula: String,
    campoEditar: String,
    nuevoValor: String,
    medicos: MutableList<Medico>
): MutableList<Medico> {
    val ind = RetornarIndices(cedula, medicos)
    val existeMedico = ind["medico"]!! > -1
    if (existeMedico) {
        val indMedico = ind["medico"] as Int
        val indPaciente = ind["paciente"] as Int
        when (campoEditar) {
            "telefono" -> {
                medicos[indMedico].pacientes?.get(indPaciente)?.telefono = nuevoValor
            }
            "pacientefrecuente" -> {
                medicos[indMedico].pacientes?.get(indPaciente)?.pacientefrecuente = nuevoValor.toBoolean()
            }
        }
    }
    return medicos
}

fun buscarPaciente(
    parametro: String,
    datoConsulta: String,
    listaMedicos: MutableList<Medico>
): List<List<Any>?> {
    var medicoEncontrado: List<List<Any>?> = emptyList()
    when (parametro) {
        "nombre" -> {
            medicoEncontrado = listaMedicos
                .map { medico: Medico ->
                    val medicoYpaciente = medico.pacientes?.filter { paciente: Paciente ->
                        return@filter paciente.nombre == datoConsulta
                    }
                    return@map medicoYpaciente?.let { listOf<Any>(medico, it) }
                }.filter { list: List<Any>? ->
                    return@filter list != null
                }.filter { list ->
                    val pacientefiltrado = list?.get(1) as List<Paciente>
                    return@filter pacientefiltrado.size > 0
                }
        }
        else -> {
            JOptionPane.showMessageDialog(null, "Busqueda por ${parametro} no fue encontrado")
        }
    }
    return medicoEncontrado
}

fun RetornarIndices(
    cedula: String,
    medico: MutableList<Medico>
): Map<String, Int?> {
    val respuesta = medico
        .map { medico: Medico ->
            val medicoYpaciente = medico.pacientes?.filter { paciente: Paciente ->
                return@filter paciente.cedula == cedula
            }
            return@map medicoYpaciente?.let { listOf<Any>(medico, it) }
        }.filter { list: List<Any>? ->
            return@filter list != null
        }.filter { list ->
            val pacientefiltrado = list?.get(1) as List<Paciente>
            return@filter pacientefiltrado.size > 0
        }

    val encontroRespuesta = respuesta.size > 0
    if (!encontroRespuesta) {
        JOptionPane.showMessageDialog(null, "No se encontro paciente con cedula: ${cedula}")
        return mapOf<String, Int?>("medico" to -1, "paciente" to -1)
    }
    val indMedico = medico.indexOf(respuesta[0]?.get(0))
    val paciente = respuesta[0]?.get(1) as List<Paciente>
    val indpaciente = medico[indMedico].pacientes?.indexOf(paciente[0])
    val indice = mapOf<String, Int?>("empresa" to indMedico, "empleado" to indpaciente)
    return indice
}