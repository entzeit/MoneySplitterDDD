package main.kotlin.application.usecase

import main.kotlin.domain.model.Balances
import main.kotlin.domain.model.Bill
import main.kotlin.domain.service.BalanceCalculator

class CalculateBalancesUseCase(
    private val calculator: BalanceCalculator
) {
    fun execute(bills: List<Bill>): Balances {
        return calculator.calculate(bills)
    }
}