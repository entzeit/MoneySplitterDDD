package main.kotlin.domain.service

import main.kotlin.domain.model.Balance
import main.kotlin.domain.model.person.Person
import main.kotlin.domain.model.Transaction

class TransactionCalculator {

    fun calculate(balances: List<Balance>): List<Transaction> {
        val mutable = balances.associateBy { it.person }
            .mapValues { it.value.amount }
            .toMutableMap()
        val result = mutableListOf<Transaction>()
        while (mutable.isNotEmpty()) {
            val min = mutable.minByOrNull { it.value }!!
            val max = mutable.maxByOrNull { it.value }!!
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
        debtorBalance: Long,
        creditorBalance: Long
    ): Long {
        val maxTransfer = kotlin.math.abs(debtorBalance)
        return if (creditorBalance >= maxTransfer) {
            maxTransfer
        } else {
            creditorBalance
        }
    }

    private fun update(
        balances: MutableMap<Person, Long>,
        from: Person,
        to: Person,
        amount: Long
    ) {
        balances[from] = balances[from]!! + amount
        balances[to] = balances[to]!! - amount
        if (balances[from] == 0L) balances.remove(from)
        if (balances[to] == 0L) balances.remove(to)
    }
}