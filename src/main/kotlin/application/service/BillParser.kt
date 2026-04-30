package main.kotlin.application.service

import main.kotlin.domain.model.vo.PersonName

class BillParser {

    private val regex = Regex("""^\p{L}+ \d+,\d{2} \p{L}+(,\p{L}+)*\s*$""")

    fun parse(line: String): ParsedBill {
        require(regex.matches(line)) { "Invalid line format: $line" }
        val parts = line.split(" ")

        return ParsedBill(
            payerName = PersonName.create(parts[0]),
            amount = parts[1].toCents(),
            debtorNames = parts[2].split(",").map { PersonName.create(it) }
        )
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

data class ParsedBill( //clean, but not domain-safe
    val payerName: PersonName,
    val amount: Long,
    val debtorNames: List<PersonName>
)