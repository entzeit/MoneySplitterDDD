package main.kotlin.moneysplitter.application.parser

import main.kotlin.moneysplitter.application.dto.ParsedBill
import main.kotlin.moneysplitter.domain.model.vo.Money
import main.kotlin.moneysplitter.domain.model.vo.PersonName

//DTO: Data Transfer Object
class BillParser {

    private val regex = Regex("""^\p{L}+ \d+,\d{2} \p{L}+(,\p{L}+)*\s*$""")

    fun parse(line: String): ParsedBill {
        require(regex.matches(line)) { "Invalid line format: $line" }
        val parts = line.split(" ")

        return ParsedBill(
            payerName = PersonName.Companion.create(parts[0]).getOrThrow(),
            amount = Money.Companion.ofCents(parts[1].toCents()), //todo: getOrThrow()
            debtorNames = parts[2].split(",").map { PersonName.Companion.create(it).getOrThrow() }
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