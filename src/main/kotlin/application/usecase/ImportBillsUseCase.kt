package main.kotlin.application.usecase

import main.kotlin.application.service.BillParser
import main.kotlin.domain.model.Bill
import main.kotlin.domain.model.person.Person
import main.kotlin.domain.model.vo.PersonId
import main.kotlin.domain.model.vo.PersonName
import main.kotlin.domain.repository.PersonRepository

class ImportBillsUseCase(
    private val parser: BillParser, //manual dependency injection
    private val personRepository: PersonRepository //manual dependency injection
) {

    fun execute(lines: List<String>): List<Bill> {
        return lines.map { line ->
            val parsed = parser.parse(line)

            val payer = resolvePerson(parsed.payerName)
            val debtors = parsed.debtorNames.map { resolvePerson(it) }

            Bill.create(payer, parsed.amount, debtors)
        }
    }

    private fun resolvePerson(name: PersonName): Person {
        return personRepository.findByName(name)
            ?: Person(PersonId.new(),name).also { personRepository.save(it) }
    }
}