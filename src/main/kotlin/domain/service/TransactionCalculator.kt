package main.kotlin.domain.service

import main.kotlin.domain.model.Transaction
import main.kotlin.domain.model.vo.Money
import main.kotlin.domain.model.vo.PersonId

class TransactionCalculator {

    fun calculate(balances: Map<PersonId, Money>): List<Transaction> { //todo:
        // Work on a mutable copy (pure from outside perspective) todo: why work on mutable copy?
        val mutable = balances
            .filterValues { it.cents != 0L } // ignore zero balances
            .toMutableMap()
        val result = mutableListOf<Transaction>()
        while (mutable.isNotEmpty()) {
            val min = mutable.minByOrNull { it.value.cents }!!
            val max = mutable.maxByOrNull { it.value.cents }!!

            // Stop if all are settled
            if (min.value.cents >= 0) break //todo: not really necessary because it always adds up

            val amount = calculateSettlement(min.value, max.value)
            result.add(
                Transaction(
                    from = min.key,
                    to = max.key,
                    amount = amount
                )
            )
            update(mutable, min.key, max.key, amount)
        }
        return result
    }

    private fun calculateSettlement(
        debtorBalance: Money,
        creditorBalance: Money
    ): Money {
        val maxTransfer = debtorBalance.abs()
        return if (creditorBalance >= maxTransfer) {
            maxTransfer
        } else {
            creditorBalance
        }
    }

    private fun update(
        balances: MutableMap<PersonId, Money>,
        from: PersonId,
        to: PersonId,
        amount: Money
    ) {
        balances[from] = balances[from]!! + amount
        balances[to] = balances[to]!! - amount
        if (balances[from] == Money(0L)) balances.remove(from)
        if (balances[to] == Money(0L)) balances.remove(to)
    }
}