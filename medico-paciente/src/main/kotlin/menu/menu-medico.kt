package menu

import Medico
import archivo.escribirEnArchivo
import archivo.leerArchivo
import buscarMedico
import crearMedico
import editarMedico
import eliminarMedico
import listarInformacion
import javax.swing.JOptionPane

fun stringMenuInicio(): String {
    return "     BIENVENIDO AL CENTRO DE SALUD NAYON      \n\n" +
            "MENU DE OPCIONES:\n \n" +
            "1. Crear Médico\n" +
            "2. Actualizar Médico\n" +
            "3. Eliminar Médico\n" +
            "4. Buscar Médico por atributo\n" +
            "5. Listar todas l@s Médicos\n" +
            "6. Gestionar Pacientes\n" +
            "7. Salir\n"
}

fun crearMedico() {

    val nombre = JOptionPane.showInputDialog("Ingrese el Nombre")
    val ruc = JOptionPane.showInputDialog("Ingrese el ruc del Médico")
    val telefono = JOptionPane.showInputDialog("Ingrese el telefono del Médico")
    val fechanacimiento = JOptionPane.showInputDialog("Ingrese la fecha de nacimiento del Médico")
    val consulta = JOptionPane.showInputDialog("Ingrese el precio de consulta")
    val datos = leerArchivo()
    val medicoNuevo = crearMedico(ruc,nombre,telefono.toInt(),fechanacimiento,consulta.toDouble())
    val nuevo = datos + medicoNuevo
    escribirEnArchivo(nuevo)
}

fun editarMedico() {
    val nombre = JOptionPane.showInputDialog("Ingrese nombre del Médico a editar")
    val campo = JOptionPane.showInputDialog("Ingrese campo del Médico a editar")
    val nuevoValor = JOptionPane.showInputDialog("Ingrese el nuevo valor")
    val datos = leerArchivo()
    val medicoEditado = editarMedico(nombre, campo, nuevoValor, datos)
    escribirEnArchivo(medicoEditado)
}

fun eliminarMedicol() {
    val nombre = JOptionPane.showInputDialog("Ingrese el nombre deL Médico a eliminar")
    val datos = leerArchivo()
    val eliminarMedico = eliminarMedico(nombre, datos)
    escribirEnArchivo(eliminarMedico)
}

fun buscarMedico() {
    val campo = JOptionPane.showInputDialog("Ingrese campo por el que desea buscar")
    val consulta = JOptionPane.showInputDialog("Ingrese su busqueda")
    val datos = leerArchivo()
    val medicoEncontrado = buscarMedico(campo, consulta, datos)
    val existe = medicoEncontrado.size > 0
    if (existe) {
        var menuRespuesta = ""
        medicoEncontrado.forEach { empresa: Medico? ->
            menuRespuesta += "-------------------------------------------------\n" +
                    "Nombre: ${empresa?.nombre}\n" +
                    "Fecha Nacimiento: ${empresa?.fechanacimiento}\n" +
                    "Numero de Pacientes: ${empresa?.pacientes?.size}\n"
        }
        menuRespuesta += "-------------------------------------------------\n"
        JOptionPane.showMessageDialog(null, menuRespuesta)
    } else {
        JOptionPane.showMessageDialog(null, "Medico NO encontrado")
    }
}

fun mostrarTodo() {
    val total = listarInformacion()
    JOptionPane.showMessageDialog(null, total)
}