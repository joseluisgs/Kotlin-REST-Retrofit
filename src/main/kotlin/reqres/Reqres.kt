package reqres

import kotlinx.coroutines.runBlocking

object Reqres {
    fun run() = runBlocking {
        println("API REST Reqres.in - https://reqres.in/")
        println("Resources: Users")
        println("------------------------------------------------------")
    }
}