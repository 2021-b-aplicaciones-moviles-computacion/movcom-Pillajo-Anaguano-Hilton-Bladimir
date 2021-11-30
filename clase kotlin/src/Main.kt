import java.util.*
import kotlin.collections.ArrayList

fun main() {
    println("Hola mundo") //; no son requeridos
    //Tipo nombre = valor;
    //Int edad = 12;

    //Tipo de variable

    //INMUTABLES(val)
    val inmutable: String = "Adrian"
    //inmutable = "vicente"

    //MUTABLE (var)
    var mutable: String = "vicente"
    mutable = "Adrian"

    // siempre se va a preferir lo inmutable antes que lo mutable

    //val > var
    //Sintaxis y Duck Typing(asume automaticamente si es un Strig, entero , ,......
    val ejemplovariable = "Nombre"
    var edadejemplo = 12

    // Tipos de variables de Java
    val nombreprofesor: String = "Adrian Eguez"
    val sueldo: Double = 1.2
    val estadocivil: Char = 'S'
    val fechadenacimiento: Date = Date()

    //Condicionales
    if (true){
        //verdadero
    }else{
        //falso
    }

    //witch Estados -> S -> C -> :::::
    val estadoCivil: String = "S"
    when(estadoCivil){
        ("S") -> {
            println("Acercarse")
        }
        ("C") -> {
            println("Alejarse")
        }
        "UN" -> println("Hablar")
        else -> println("No Reconocido")
    }

    val coqueteo = if (estadoCivil == "S") "SI" else "NO"

    imprimirNombre( "Adrian")
    calcularsueldo(100.00,14.00, 25.00)

    //Parametros nombrados

    //Tipos de arreglos
    val arregloestatico: Array<Int> = arrayOf(1,2,3)

    //Arreglo dinamico
    val arreglodinamico: ArrayList<Int> = arrayListOf(1,2,3,4,5,6,7,8,9,10)
    println(arreglodinamico)
    arreglodinamico.add(11)
    arreglodinamico.add(12)
    println(arreglodinamico)

    //OPERADORES
    //FOR EACH -> Unit
    //Iterar un arreglo

    val respuestaforeach: Unit = arreglodinamico
        .forEach{ valoractual: Int ->
            println("valor actual: ${valoractual}")
        }
    arreglodinamico
        .forEachIndexed{indice: Int, valoractual: Int ->
            println("Valor ${valoractual} indice: ${indice}")
        }
    println(respuestaforeach)
    //MAP -> Muta el arreglo

    val respuestamap: List<Double> = arreglodinamico
        .map { valoractual: Int ->
            return@map valoractual.toDouble() + 100.00
        }
    println(respuestamap)

    val respuestamapDos = arreglodinamico.map { it + 15 }
    println(respuestamapDos)
}

fun imprimirNombre(nombre: String): Unit {
    println("Nombre: ${nombre}")
}

fun calcularsueldo(
    sueldo: Double,
    tasa: Double = 12.00,
    bonoespecial: Double? = null,
): Double{
    if (bonoespecial == null){
        return  sueldo * (100/tasa)
    }else{
        return sueldo * (100/tasa) + bonoespecial
    }
}