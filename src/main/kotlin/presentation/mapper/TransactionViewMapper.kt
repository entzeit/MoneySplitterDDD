package main.kotlin.presentation.mapper

import main.kotlin.domain.model.Transaction
import main.kotlin.domain.repository.PersonRepository
import main.kotlin.presentation.view.TransactionView

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