package main.kotlin.application.usecase

import main.kotlin.domain.model.Balance
import main.kotlin.domain.model.Transaction
import main.kotlin.domain.service.TransactionCalculator

class CalculateTransactionsUseCase(
    private val calculator: TransactionCalculator
) {

    fun execute(balances: List<Balance>): List<Transaction> {
        return calculator.calculate(balances)
    }
}