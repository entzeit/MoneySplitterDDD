package main.kotlin.application.usecase

import main.kotlin.domain.model.Bill
import main.kotlin.domain.model.vo.Money
import main.kotlin.domain.model.vo.PersonId
import main.kotlin.domain.service.BalanceCalculator

class CalculateBalancesUseCase(
    private val calculator: BalanceCalculator
) {
    fun execute(bills: List<Bill>): Map<PersonId, Money> {
        return calculator.calculate(bills)
    }
}