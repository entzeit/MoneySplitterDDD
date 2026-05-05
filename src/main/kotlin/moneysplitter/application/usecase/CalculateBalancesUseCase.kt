package main.kotlin.moneysplitter.application.usecase

import main.kotlin.moneysplitter.domain.model.Balances
import main.kotlin.moneysplitter.domain.model.Bill
import main.kotlin.moneysplitter.domain.service.BalanceCalculator

class CalculateBalancesUseCase(
    private val calculator: BalanceCalculator //manual dependency injection
) {
    fun execute(bills: List<Bill>): Balances {
        return calculator.calculate(bills)
    }
}