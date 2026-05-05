package main.kotlin.moneysplitter.application.usecase

import main.kotlin.moneysplitter.application.parser.BillParser
import main.kotlin.moneysplitter.domain.model.Bill
import main.kotlin.moneysplitter.domain.model.Person
import main.kotlin.moneysplitter.domain.model.vo.BillId
import main.kotlin.moneysplitter.domain.model.vo.PersonId
import main.kotlin.moneysplitter.domain.model.vo.PersonName
import main.kotlin.moneysplitter.domain.repository.PersonRepository

class ImportBillsUseCase(
    private val parser: BillParser, //manual dependency injection
    private val personRepository: PersonRepository //manual dependency injection
) {

    fun execute(lines: List<String>): List<Bill> {
        return lines.map { line ->
            val parsed = parser.parse(line)

            val payer = resolvePerson(parsed.payerName)
            val debtors = parsed.debtorNames.map { resolvePerson(it) }

            Bill.create(BillId.new(), payer, parsed.amount, debtors)
        }
    }

    private fun resolvePerson(name: PersonName): Person {
        return personRepository.findByName(name)
            ?: Person(PersonId.new(), name).also { personRepository.save(it) }
    }
}