package main.kotlin.domain.service

import main.kotlin.domain.model.Balance
import main.kotlin.domain.model.Bill
import main.kotlin.domain.model.Person
import kotlin.random.Random

class BalanceManager {
    val balances: MutableMap<Person, Balance> = mutableMapOf()

    fun calculateBalances(bills: List<Bill>) {
        bills.forEach { bill ->
            val payerBalance = balances.getOrPut(bill.payer, { Balance(bill.payer, 0) })
            payerBalance.amount += bill.amount

            val debtPortion = bill.amount / bill.debtors.asList().size
            assert((bill.amount % bill.debtors.size) <= Int.MAX_VALUE)
            val remainder = (bill.amount % bill.debtors.size).toInt()

            val debtorsList = bill.debtors.map { balances.getOrPut(it, { Balance(it, 0) }) }
            debtorsList.forEach { it.amount -= debtPortion }
            val seed = 0L
            val random = Random(seed)
            debtorsList.shuffled(random).take(remainder).forEach { it.amount-- }
        }
    }
}