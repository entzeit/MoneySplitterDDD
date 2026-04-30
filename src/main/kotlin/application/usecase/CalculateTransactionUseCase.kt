package main.kotlin.application.usecase

import main.kotlin.domain.model.Transaction
import main.kotlin.domain.model.vo.Money
import main.kotlin.domain.model.vo.PersonId
import main.kotlin.domain.service.TransactionCalculator

class CalculateTransactionsUseCase(
    private val calculator: TransactionCalculator
) {

    fun execute(balances: Map<PersonId, Money>): List<Transaction> {
        return calculator.calculate(balances)
    }
}