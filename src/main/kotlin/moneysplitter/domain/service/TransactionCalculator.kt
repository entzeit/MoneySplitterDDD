package main.kotlin.moneysplitter.domain.service

import main.kotlin.moneysplitter.domain.model.Balances
import main.kotlin.moneysplitter.domain.model.Transaction
import main.kotlin.moneysplitter.domain.model.vo.Money
import main.kotlin.moneysplitter.domain.model.vo.PersonId
import main.kotlin.moneysplitter.domain.model.vo.TransactionId

class TransactionCalculator {

    fun calculate(balances: Balances, idProvider: () -> TransactionId = { TransactionId.new() }): List<Transaction> {
        // Work on a mutable copy (pure function from outside perspective)
        val mutable = balances.toMap()
            .filterValues { it.cents != 0L } // ignore zero balances
            .toMutableMap()
        val result = mutableListOf<Transaction>()
        while (mutable.isNotEmpty()) {
            val min = mutable.minByOrNull { it.value.cents }!!
            val max = mutable.maxByOrNull { it.value.cents }!!

            check(min.value.cents < 0) { "Invariant violated: balances don't sum to zero" }

            val amount = calculateSettlement(min.value, max.value)
            result.add(
                Transaction.create(
                    id = idProvider(),
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
        if (balances[from] == Money.ofCents(0L)) balances.remove(from)
        if (balances[to] == Money.ofCents(0L)) balances.remove(to)
    }
}