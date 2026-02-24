import kotlin.math.absoluteValue

class TransactionManager(val balanceManager: BalanceManager) {
    var transactions = mutableListOf<Transaction>()

    fun calculateTransactions() {
        while (balanceManager.balances.isNotEmpty()) {
            val (min, max) = balanceManager.balances.values.let { it.minByOrNull { b -> b.amount }!! to it.maxByOrNull { b -> b.amount }!! }
            val amount = calculateTransactionAmount(min.amount, max.amount)
            val transaction = Transaction(min.person, max.person, amount)
            transactions.add(transaction)
            updateBalance(transaction)
        }
    }

    private fun calculateTransactionAmount(min: Long, max: Long): Long {
        val sum = min + max
        return if (sum >= 0) {
            min.absoluteValue
        } else {
            max
        }
    }

    private fun updateBalance(transaction: Transaction) {
        val from = balanceManager.balances[transaction.from]!!
        from.amount += transaction.amount
        if (from.amount == 0.toLong()) {
            balanceManager.balances.remove(from.person)
        }
        val to = balanceManager.balances[transaction.to]!!
        to.amount -= transaction.amount
        if (to.amount == 0.toLong()) {
            balanceManager.balances.remove(to.person)
        }
    }

    override fun toString(): String =
        transactions.joinToString("\n") { transaction ->
            "${transaction.from} -> ${transaction.to}: ${toEuro(transaction.amount)}"
        }

    private fun toEuro(amount: Long): String {
        val euro: Long = amount / 100
        val cents: Long = amount - euro * 100
        var centString = ""
        if (cents < 10) {
            centString = "0"
        }
        return "${euro},${centString}${cents}"
    }
}