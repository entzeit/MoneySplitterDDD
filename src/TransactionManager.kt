import kotlin.math.absoluteValue

class TransactionManager(val balanceManager: BalanceManager) {
    var transactions = mutableListOf<Transaction>()

    fun calculateTransactions() {
        while (!balanceManager.balances.isEmpty()) {
            val min: Balance = balanceManager.balances.minBy { it.amount }
            val max: Balance = balanceManager.balances.maxBy { it.amount }
            val amount = calculateTransactionAmount(min.amount, max.amount)
            val transaction = Transaction(min.person, max.person, amount)
            transactions.add(transaction)
            updateBalance(transaction)
        }
    }

    private fun calculateTransactionAmount(min: Int, max: Int): Int {
        val sum = min + max
        return if (sum >= 0) {
            min.absoluteValue
        } else {
            max
        }
    }

    private fun updateBalance(transaction: Transaction) {
        val from = balanceManager.balances.find { it.person == transaction.from }!!
        from.amount += transaction.amount
        if (from.amount == 0) {
            balanceManager.balances.remove(from)
        }
        val to = balanceManager.balances.find { it.person == transaction.to }!!
        to.amount -= transaction.amount
        if (to.amount == 0) {
            balanceManager.balances.remove(to)
        }
    }

    override fun toString(): String =
        transactions.joinToString("\n") { transaction ->
            "${transaction.from} -> ${transaction.to}: ${toEuro(transaction.amount)}"
        }

    private fun toEuro(amount: Int): String {
        val euro: Int = amount / 100
        val cents: Int = amount - euro * 100
        var centString = ""
        if (cents < 10) {
            centString = "0"
        }
        return "${euro},${centString}${cents}"
    }
}