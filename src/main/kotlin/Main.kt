import kotlinx.coroutines.runBlocking
import reqres.Reqres
import kotlin.system.exitProcess

// runBlocking porque hay funcionmes suspendidas, no termina el main hasta que no terminen todas las funciones
fun main(): Unit = runBlocking {
    println("HOLA API REST con Kotlin y Retrofit2")
    // PlaceHolder
    // PlaceHolder.run()
    // Reqres
    Reqres.run()
    println("Fin del programa")
    exitProcess(0)
}
