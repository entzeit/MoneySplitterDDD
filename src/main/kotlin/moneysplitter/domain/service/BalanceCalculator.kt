package main.kotlin.moneysplitter.domain.service

import main.kotlin.moneysplitter.domain.model.Balances
import main.kotlin.moneysplitter.domain.model.Bill
import main.kotlin.moneysplitter.domain.model.vo.Money
import main.kotlin.moneysplitter.domain.model.vo.PersonId
import kotlin.random.Random

class BalanceCalculator {
    fun calculate(bills: List<Bill>): Balances {
        val result = mutableMapOf<PersonId, Money>()
        bills.forEach { bill ->
            val payerId = bill.payer.id
            val payerAmount = bill.amount
            result[payerId] = (result[payerId] ?: Money.Companion.ofCents(0)) + payerAmount

            val share = Money.Companion.ofCents(bill.amount.cents / bill.debtors.size)
            val remainder = Money.Companion.ofCents(bill.amount.cents % bill.debtors.size)
            val debtorIds = bill.debtors.map { it.id }
            debtorIds.forEach { debtorId ->
                result[debtorId] = (result[debtorId] ?: Money.Companion.ofCents(0)) - share
            }

            applyRemainder(debtorIds, remainder, result)
        }
        return Balances(result.toMap()) //toMap() important, make MutableMap → immutable Map
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
                val current = balances.getValue(debtorId)
                balances[debtorId] = current - Money.Companion.ofCents(1)
            }
    }
}