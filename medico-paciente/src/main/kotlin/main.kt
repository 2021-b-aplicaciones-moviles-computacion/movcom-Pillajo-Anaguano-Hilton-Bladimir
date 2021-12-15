import menu.*
import javax.swing.JOptionPane

fun main(args: Array<String>) {
    menu()
}


fun menu() {
    val menuInicio = stringMenuInicio()
    do {
        val entradaTexto = JOptionPane.showInputDialog(menuInicio)
        val opcion = Integer.parseInt(entradaTexto)
        when (opcion) {
            1 -> {
                crearMedico()
            }
            2 -> {
                editarMedico()
            }
            3 -> {
                eliminarMedicol()
            }
            4 -> {
                buscarMedico()
            }
            5 -> {
                mostrarTodo()
            }
            6 -> {
                menuEmpleados()
            }
            7 ->{
                JOptionPane.showMessageDialog(null, "GRACIAS POR VISITARNOS")

            }
            else -> {
                JOptionPane.showMessageDialog(null, "Opcion seleccionada no existe")
            }
        }
    } while (opcion != 7)
}

fun menuEmpleados() {
    val menuInicioEstudiante = stringMenuInicioEstudiante()
    do {
        val entradaTexto = JOptionPane.showInputDialog(menuInicioEstudiante)
        val opcion = Integer.parseInt(entradaTexto)
        when (opcion) {
            1 -> {
                crearPaciente()
            }
            2 -> {
                editarPaciente()
            }
            3 -> {
                eliminarPaciente()
            }
            4 -> {
                buscarPaciente()
            }
            5 -> {
                menu()
            }
            6 -> {
                mostrarTodo()
            }
            0 -> {
                if( regresarMenuPrincipal() == true){
                    menu()
                } else {
                    JOptionPane.showMessageDialog(null, "Ha seleccionado la opcion no, siga administradndo pacientes")
                }
            }
            else -> {
                JOptionPane.showMessageDialog(null, "Opcion seleccionada no existe")
            }
        }
    } while (opcion == 0)
}

fun regresarMenuPrincipal(): Boolean {
    val opciones = arrayOf("Si", "No")
    val respuesta = JOptionPane.showOptionDialog(null, "Esta seguro que desea regresar al menu principal ",
        "Regresar Menu Principal",
        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0])
    return respuesta == 0
}