package main.kotlin.domain.service

import main.kotlin.domain.model.Balance
import main.kotlin.domain.model.Bill
import main.kotlin.domain.model.Person
import kotlin.random.Random

class BalanceCalculator {
    fun applyBill(
        bill: Bill,
        balances: MutableMap<Person, Balance>
    ) {
        val payerBalance = balances.getOrPut(bill.payer) {
            Balance(bill.payer, 0)
        }
        payerBalance.amount += bill.amount

        val debtPortion = bill.amount / bill.debtors.size
        val remainder = (bill.amount % bill.debtors.size).toInt()

        val debtorsList = bill.debtors.map {
            balances.getOrPut(it) { Balance(it, 0) }
        }
        debtorsList.forEach { it.amount -= debtPortion }
        applyRemainder(debtorsList, remainder)
    }

    private fun applyRemainder(
        debtors: List<Balance>,
        remainder: Int
    ) {
        val random = Random(0L)
        debtors.shuffled(random)
            .take(remainder)
            .forEach { it.amount-- }
    }
}