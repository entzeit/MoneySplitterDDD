package main.kotlin.moneysplitter.application.usecase

import main.kotlin.moneysplitter.domain.model.Balances
import main.kotlin.moneysplitter.domain.model.Transaction
import main.kotlin.moneysplitter.domain.service.TransactionCalculator

class CalculateTransactionsUseCase(
    private val calculator: TransactionCalculator
) {

    fun execute(balances: Balances): List<Transaction> {
        return calculator.calculate(balances)
    }
}