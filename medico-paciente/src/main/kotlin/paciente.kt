import javax.swing.JOptionPane

class Paciente(
    var cedula: String,
    var nombre: String,
    var telefono: String,
    var fechaNacimiento: String,
    var pacientefrecuente: Boolean = true
) {

}

fun buscarPaciente(
    parametro: String,
    datoConsulta: String,
    listaMedicos: MutableList<Medico>
): List<List<Any>?> {
    var medicosEncontrados: List<List<Any>?> = emptyList()
    when (parametro) {
        "nombre" -> {
            medicosEncontrados = listaMedicos
                .map { medico: Medico ->
                    val medicoYpaciente = medico.pacientes?.filter { paciente: Paciente ->
                        return@filter paciente.nombre == datoConsulta
                    }
                    return@map medicoYpaciente?.let { listOf<Any>(medico, it) }
                }.filter { list: List<Any>? ->
                    return@filter list != null
                }.filter { list ->
                    val empleadosFiltrados = list?.get(1) as List<Paciente>
                    return@filter empleadosFiltrados.size > 0
                }
        }
        "cedula" -> {
            medicosEncontrados = listaMedicos
                .map { medico: Medico ->
                    val medicoYpaciente = medico.pacientes?.filter { paciente: Paciente ->
                        return@filter paciente.cedula == datoConsulta
                    }
                    return@map medicoYpaciente?.let { listOf<Any>(medico, it) }
                }.filter { list: List<Any>? ->
                    return@filter list != null
                }.filter { list ->
                    val pacientesfiltrados = list?.get(1) as List<Paciente>
                    return@filter pacientesfiltrados.size > 0
                }
        }
        else -> {
            JOptionPane.showMessageDialog(null, "Parametro ${parametro} no encontrado")
        }
    }
    return medicosEncontrados
}

fun editarPaciente(
    cedula: String,
    campoAEditar: String,
    nuevoValor: String,
    medicos: MutableList<Medico>
): MutableList<Medico> {
    val indices = buscarYRetornarIndices(cedula, medicos)
    val existeMedico = indices["Medico"]!! > -1
    if (existeMedico) {
        val indMedico = indices["Medico"] as Int
        val indPaciente = indices["Paciente"] as Int
        when (campoAEditar) {
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


fun crearPaciente(
    nombreMedico: String,
    paciente: Paciente,
    medicos: MutableList<Medico>
): MutableList<Medico> {
    val indMedico = buscarYRetornarIndice(nombreMedico, medicos)
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
    val indices = buscarYRetornarIndices(cedula, medicos)
    val existeMedico = indices["Medico"]!! > -1
    val existePaciente = indices["Paciente"]!! > -1

    if (existeMedico && existePaciente) {
        val indMedico = indices["Medico"] as Int
        val indPaciente = indices["Paciente"] as Int
        medicos[indMedico].pacientes?.removeAt(indPaciente)
    }
    return medicos
}

fun buscarYRetornarIndices(
    cedula: String,
    medicos: MutableList<Medico>
): Map<String, Int?> {
    val respuesta = medicos
        .map { medico: Medico ->
            val medicoYpaciente = medico.pacientes?.filter { paciente: Paciente ->
                return@filter paciente.cedula == cedula
            }
            return@map medicoYpaciente?.let { listOf<Any>(medico, it) }
        }.filter { list: List<Any>? ->
            return@filter list != null
        }.filter { list ->
            val pacientesFiltrados = list?.get(1) as List<Paciente>
            return@filter pacientesFiltrados.size > 0
        }

    val encontroRespuesta = respuesta.size > 0
    if (!encontroRespuesta) {
        JOptionPane.showMessageDialog(null, "No se encontro paciente con cedula: ${cedula}")
        return mapOf<String, Int?>("medico" to -1, "paciente" to -1)
    }
    val indMedico = medicos.indexOf(respuesta[0]?.get(0))
    val paciente = respuesta[0]?.get(1) as List<Paciente>
    val indPaciente = medicos[indMedico].pacientes?.indexOf(paciente[0])
    val indices = mapOf<String, Int?>("medico" to indMedico, "paciente" to indPaciente)
    return indices
}