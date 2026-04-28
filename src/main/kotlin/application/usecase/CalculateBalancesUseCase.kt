package main.kotlin.application.usecase

import main.kotlin.domain.model.Balance
import main.kotlin.domain.model.Bill
import main.kotlin.domain.model.Person
import main.kotlin.domain.service.BalanceCalculator

class CalculateBalancesUseCase(
    private val calculator: BalanceCalculator
) {
    fun execute(bills: List<Bill>): MutableMap<Person, Balance> {
        val balances = mutableMapOf<Person, Balance>()
        bills.forEach { bill ->
            calculator.applyBill(bill, balances)
        }
        return balances
    }
}