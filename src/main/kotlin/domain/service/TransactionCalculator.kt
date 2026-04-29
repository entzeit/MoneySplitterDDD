package main.kotlin.domain.service

import main.kotlin.domain.model.Balance
import main.kotlin.domain.model.Transaction
import main.kotlin.domain.model.person.Person
import main.kotlin.domain.model.vo.Money

class TransactionCalculator {

    fun calculate(balances: List<Balance>): List<Transaction> {
        val mutable = balances.associateBy { it.person }
            .mapValues { it.value.amount }
            .toMutableMap()
        val result = mutableListOf<Transaction>()
        while (mutable.isNotEmpty()) {
            val min = mutable.minByOrNull { it.value.cents }!!
            val max = mutable.maxByOrNull { it.value.cents }!!
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
        balances: MutableMap<Person, Money>,
        from: Person,
        to: Person,
        amount: Money
    ) {
        balances[from] = balances[from]!! + amount
        balances[to] = balances[to]!! - amount
        if (balances[from] == Money(0L)) balances.remove(from)
        if (balances[to] == Money(0L)) balances.remove(to)
    }
}