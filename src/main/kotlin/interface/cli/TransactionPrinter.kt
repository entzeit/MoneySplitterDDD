package main.kotlin.`interface`.cli

import main.kotlin.domain.model.Transaction
import main.kotlin.domain.repository.PersonRepository

class TransactionPrinter(
    private val personRepository: PersonRepository
) {
    fun print(transactions: List<Transaction>) {
        transactions.forEach {
            val fromName = personRepository.findById(it.from)?.name()
                ?: "Unknown(${it.from})"

            val toName = personRepository.findById(it.to)?.name()
                ?: "Unknown(${it.to})"

            println("$fromName -> $toName: ${it.amount.formatToEuro()}")
        }
    }
}