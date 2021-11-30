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

    // Filter -> Filtrar el arreglo
    // 1) devuelve una expresion (true o false)
    // 2) nuevo arreglo filtrado

    val respuestafilter: List<Int> = arreglodinamico
        .filter { valoractual: Int ->
            val mayoresacienco: Boolean = valoractual > 5 //expresion condicion
            return@filter mayoresacienco
        }
    val respuestafilterDos = arreglodinamico.filter { it <= 5 }
    println(respuestafilter)
    println(respuestafilterDos)

    //Operador OR AND
    //OR -> ANY (alguno cumple
    // AND -> ALL (todos cumplen

    val respuestaAny: Boolean = arreglodinamico
        .any{ valoractual: Int ->
            return@any (valoractual > 5)
        }
    println(respuestaAny) // true

    val respuestaAll: Boolean = arreglodinamico
        .all{ valoractual: Int ->
            return@all (valoractual > 5)
        }
    println(respuestaAll) // false

    //REDUCE -> valor acumulado
    //valor acumulado = 0 ( siempre 0 en lenguaje kotlin)
    // suma todos los valores del arreglo

    val respuestareduce: Int = arreglodinamico
        .reduce{
            acumulado: Int, valoractual: Int ->
            return@reduce (acumulado + valoractual) // logica de negocio
        }
    println(respuestareduce) // 78

    //100
    // [12,15,8,10]
    val arreglodanio = arrayListOf<Int>(12,15,8,10)
    val respuestareducefold = arreglodanio
        .fold(
            100, //acumulado inicial
            {acumulado, valoractualiteracion ->
                return@fold acumulado - valoractualiteracion
            }
        )
    println(respuestareducefold)

    val vidaactual: Double = arreglodinamico
        .map { it * 2.3 } //arreglo
        .filter { it > 20 } // arreglo
        .fold(100.00, { acc, i -> acc - i}) //valor
        .also { println(it) }//ejecutar codigo extra
    println("Vlor actual de vida ${vidaactual}")//3.4



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

abstract class numerosjava{
    protected val numerouno: Int //propiedad de la clase
    private val numerodos: Int //propiedad de la clase
    constructor(
        uno: Int, //parametros requeridos
        dos: Int, //parametros requeridos
    ){
        numerouno = uno
        numerodos = dos
        println("inicializar")
    }
}

abstract class numeros(
    //constructor primario
    protected var numerouno: Int,//propiedad de la clase
    protected var numerodos: Int,//propiedad de la clase
){
    init{//bloque de inicio del constructor primario
        println("Inicializar")
    }
}

class Suma(
    //Cnstructor primario
    uno: Int, //parametro requerido
    dos: Int, //parametro requerido
): Numeros(uno, dos){
    init {//es el bloque del codigo del constructor primario
        this.numeroUno
        this.numeroDos
        // x -> this.uno -> NO EXISTE
        // x -> this.dos -> NO EXISTE
    }

    //public  fun sumar(): Int{
    fun sumar(): Init{
        // val total: Int = this.numeroUno + this.numeroDos
        val total: Int = numeroUno + numeroDos //en kotlin no es necesario el uso de la palabra reservada this, ya que el
        // lenguaje entiende que se esta trabajando dentro de una clase
        return  total
    }