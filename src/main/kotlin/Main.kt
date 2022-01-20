import com.diogonunes.jcolor.Ansi.colorize
import com.diogonunes.jcolor.Attribute.*
import kotlinx.coroutines.runBlocking
import placeholder.PlaceHolder
import reqres.Reqres
import kotlin.system.exitProcess

// runBlocking porque hay funcionmes suspendidas, no termina el main hasta que no terminen todas las funciones
fun main() = runBlocking<Unit> {
    println(
        colorize(
            " \uD83D\uDC4B HOLA API REST con Kotlin y Retrofit2 ",
            YELLOW_TEXT(),
            BLACK_BACK(),
            BOLD(),
            UNDERLINE()
        )
    )
    // PlaceHolder
    PlaceHolder.run()
    // Reqres
    Reqres.run()
    println("Fin del programa")
    exitProcess(0)
}
