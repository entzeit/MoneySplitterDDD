package main.kotlin.application.mapper

import main.kotlin.domain.model.Transaction
import main.kotlin.domain.repository.PersonRepository
import main.kotlin.`interface`.cli.view.TransactionView
import kotlin.collections.map

class TransactionViewMapper(
    private val personRepository: PersonRepository
) {
    fun map(transactions: List<Transaction>): List<TransactionView> {
        return transactions.map {
            TransactionView(
                fromName = personRepository.findById(it.from)?.name()?.value ?: "Unknown",
                toName = personRepository.findById(it.to)?.name()?.value ?: "Unknown",
                amount = it.amount.formatToEuro()
            )
        }
    }
}