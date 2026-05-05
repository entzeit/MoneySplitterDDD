package main.kotlin.moneysplitter.presentation.mapper

import main.kotlin.moneysplitter.domain.model.Transaction
import main.kotlin.moneysplitter.domain.repository.PersonRepository
import main.kotlin.moneysplitter.presentation.formatter.MoneyFormatter
import main.kotlin.moneysplitter.presentation.view.TransactionView

class TransactionViewMapper(
    private val personRepository: PersonRepository
) {
    fun map(transactions: List<Transaction>): List<TransactionView> {
        return transactions.map {
            TransactionView(
                fromName = personRepository.findById(it.from)?.name()?.value ?: "Unknown",
                toName = personRepository.findById(it.to)?.name()?.value ?: "Unknown",
                amount = MoneyFormatter.toEuro(it.amount)
            )
        }
    }
}