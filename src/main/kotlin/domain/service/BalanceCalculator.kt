package main.kotlin.domain.service

import main.kotlin.domain.model.Bill
import main.kotlin.domain.model.vo.Money
import main.kotlin.domain.model.vo.PersonId
import kotlin.random.Random

class BalanceCalculator {
    fun calculate(bills: List<Bill>): Map<PersonId, Money> {
        val balances = mutableMapOf<PersonId, Money>()
        bills.forEach { bill ->
            val payerId = bill.payer.id
            val payerAmount = Money.ofCents(bill.amount)
            balances[payerId] = balances.getOrDefault(payerId, Money(0)) +
                    payerAmount //todo: understand or other way

            val share = Money.ofCents(bill.amount / bill.debtors.size)
            val remainder = Money.ofCents(bill.amount % bill.debtors.size)
            val debtorIds = bill.debtors.map { it.id }
            debtorIds.forEach { debtorId ->
                balances[debtorId] =
                    balances.getOrDefault(debtorId, Money(0)) -
                            share
            }

            applyRemainder(debtorIds, remainder, balances)
        }
        return balances
    }

    private fun applyRemainder(
        debtors: List<PersonId>,
        remainder: Money,
        balances: MutableMap<PersonId, Money>
    ) {
        val random = Random(0L)
        debtors.shuffled(random)
            .take(remainder.cents.toInt())
            .forEach { debtorId ->
                balances[debtorId] =
                    balances.getOrDefault(debtorId, Money(0)) - Money(1) //todo: what if I am sure that it exists?
            }
    }
}