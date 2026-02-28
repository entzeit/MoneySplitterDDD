import java.io.IOException
import kotlin.text.split

class BillFactory(private val registry: PersonRegistry) {
    fun fromFile(fileName: String): List<Bill> {
        val bills = try {
            java.io.File(fileName).readLines().map { parse(it) }
        } catch (e: IOException) {
            println("Failed to read file: ${e.message}")
            emptyList()
        }
        return bills
    }

    private fun parse(input: String): Bill {
        val regex = Regex("""^\p{L}+ \d+,\d{2} \p{L}+(,\p{L}+)*\s*$""")
        require(regex.matches(input)) { "Invalid line format: $input" }
        val parts = input.split(" ")
        val payer = registry.findPersonByName(parts[0]) ?: registry.addPerson(Person(parts[0]))
        val amount = parts[1].toCents()
        val debtorsList = parts[2].split(",").map { name ->
            registry.findPersonByName(name) ?: registry.addPerson(Person(name.trim()))
        }
        return Bill(payer, amount, Debtors.of(debtorsList))
    }

    private fun String.toCents(): Long {
        val (euros, centsPart) = this.split(",").let { it[0] to it.getOrNull(1) }
        val cents = when {
            centsPart == null -> 0
            centsPart.length == 1 -> centsPart.toLong() * 10
            else -> centsPart.toLong()
        }
        return euros.toLong() * 100 + cents
    }
}