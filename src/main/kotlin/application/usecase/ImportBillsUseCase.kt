package main.kotlin.application.usecase

import main.kotlin.domain.model.Bill
import main.kotlin.domain.model.Person
import main.kotlin.application.service.BillParser
import main.kotlin.domain.repository.PersonRepository

class ImportBillsUseCase(
    private val parser: BillParser,
    private val personRepository: PersonRepository
) {

    fun execute(lines: List<String>): List<Bill> {
        return lines.map { line ->
            val parsed = parser.parse(line)

            val payer = resolvePerson(parsed.payerName)
            val debtors = parsed.debtorNames.map { resolvePerson(it) }

            Bill.create(payer, parsed.amount, debtors)
        }
    }

    private fun resolvePerson(name: String): Person {
        return personRepository.findByName(name)
            ?: Person(name).also { personRepository.save(it) }
    }
}