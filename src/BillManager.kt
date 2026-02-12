import java.io.File
import kotlin.io.readLines

class BillManager (fileName: String) {
    val bills: List<Bill>

    init {
        bills = try {
            File(fileName).readLines().map { line ->
                val splittedLine = line.split(" ")
                Bill(splittedLine[0], splittedLine[1], splittedLine[2])
            }
        } catch (e: Exception) {
            println("readLine() failed: ${e.message}")
            emptyList()
        }
    }
}