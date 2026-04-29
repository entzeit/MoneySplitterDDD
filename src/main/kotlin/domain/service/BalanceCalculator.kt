package main.kotlin.domain.service

import main.kotlin.domain.model.Balance
import main.kotlin.domain.model.Bill
import main.kotlin.domain.model.findByPerson
import main.kotlin.domain.model.vo.Money
import kotlin.random.Random

class BalanceCalculator {
    fun applyBill(
        bill: Bill,
        balances: MutableList<Balance>
    ) {
        val payerBalance = balances.findByPerson(bill.payer)
            ?: Balance(bill.payer).also {
                balances.add(it)
            }
        payerBalance.amount += Money.ofCents(bill.amount)

        val debtPortion = bill.amount / bill.debtors.size
        val remainder = (bill.amount % bill.debtors.size).toInt()

        val debtorsList = bill.debtors.map { person ->
            balances.findByPerson(person)
                ?: Balance(person).also { balances.add(it) }
        }
        debtorsList.forEach { it.amount -= Money.ofCents(debtPortion) }
        applyRemainder(debtorsList, remainder)
    }

    private fun applyRemainder(
        debtors: List<Balance>,
        remainder: Int
    ) {
        val random = Random(0L)
        debtors.shuffled(random)
            .take(remainder)
            .forEach { it.amount -= Money(1) }
    }
}