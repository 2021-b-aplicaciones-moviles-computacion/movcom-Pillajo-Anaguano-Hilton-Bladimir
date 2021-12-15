package menu

import Paciente
import Medico
import archivo.escribirEnArchivo
import archivo.leerArchivo
import buscarPaciente
import crearPaciente
import editarPaciente
import eliminarPaciente
import javax.swing.JOptionPane


fun stringMenuInicioEstudiante(): String {
    return "      Gestión de Pacientes      \n\n" +
            "MENU DE OPCIONES :\n \n" +
            "1. Crear Paciente\n" +
            "2. Actualizar Paciente\n" +
            "3. Eliminar Paciente\n" +
            "4. Buscar Paciente por atributo\n" +
            "5. Volver a Gestión de Medicos\n" +
            "6. Mostrar datos\n" +
            "0. Volver a menu Medicos\n"
}

fun crearPaciente() {
    val datos = leerArchivo()
    val nombreMedico = JOptionPane.showInputDialog("Ingrese el nombre del Médico para agendar al paciente")
    val cedula = JOptionPane.showInputDialog("Ingrese el número de cedula del paciente")
    val nombre = JOptionPane.showInputDialog("Ingrese el nombre del paciente")
    val telefono = JOptionPane.showInputDialog("Ingrese el telefono del paciente")
    val fechaNacimiento = JOptionPane.showInputDialog("Ingrese la fecha de nacimiento del paciente")
    val pacientefrecuente = estadoTrabajo()
    val paciente = Paciente(cedula,nombre, telefono,fechaNacimiento, pacientefrecuente)
    val pacienteCreado = crearPaciente(nombreMedico, paciente, datos)
    escribirEnArchivo(pacienteCreado)
}

fun editarPaciente() {
    val datos = leerArchivo()
    val cedula = JOptionPane.showInputDialog("Ingrese el numero de cedula del paciente a editar")
    val campo = JOptionPane.showInputDialog("Ingrese el campo del paciente a editar")
    val nuevoValor = JOptionPane.showInputDialog("Ingrese el nuevo valor del paciente a editar")
    val respuesta = editarPaciente(cedula, campo, nuevoValor, datos)
    escribirEnArchivo(respuesta)
}

fun eliminarPaciente() {
    val datos = leerArchivo()
    val cedula = JOptionPane.showInputDialog("Ingrese el numero de cedula del paciente a eliminar")
    val respuesta = eliminarPaciente(cedula, datos)
    escribirEnArchivo(respuesta)
}

fun buscarPaciente() {
    val datos = leerArchivo()
    val campo = JOptionPane.showInputDialog("Ingrese el parametro por el que desea buscar")
    val consulta = JOptionPane.showInputDialog("Ingrese el dato a buscar")
    val respuesta = buscarPaciente(campo, consulta, datos)
    var stringRespuesta = ""
    respuesta.forEach { list ->
        val medico: Medico = list?.get(0) as Medico
        val arregloPacientes: List<Paciente> = list?.get(1) as List<Paciente>
        val nombrePaciente = arregloPacientes.map { paciente: Paciente ->
            val arregloDatos = mutableMapOf<String, Any>();
            arregloDatos.put("Cedula", paciente.cedula)
            arregloDatos.put("Nombre", paciente.nombre)
            arregloDatos.put("Telefono", paciente.telefono)
            arregloDatos.put("Fecha Nacimiento", paciente.fechaNacimiento)
            arregloDatos.put("Paciente Frecuente", paciente.pacientefrecuente)
            return@map arregloDatos
        }
        stringRespuesta += "-----------------------------------------------------------------------------------------\n" +
                "Medico: ${medico.nombre}\n" +
                "Paciente: ${nombrePaciente}\n"
    }
    stringRespuesta += "-----------------------------------------------------------------------------------------\n"
    JOptionPane.showMessageDialog(null, stringRespuesta)
}



fun estadoTrabajo(): Boolean {
    val opciones = arrayOf("Si", "No")
    val respuesta = JOptionPane.showOptionDialog(null, "Paciente Frecuente ",
        "Estado Activo",
        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0])
    return respuesta == 0
}
