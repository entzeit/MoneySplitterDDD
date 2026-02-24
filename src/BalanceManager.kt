import kotlin.random.Random

class BalanceManager {
    val balances: MutableSet<Balance> = mutableSetOf() //doesn't add if already exists
    fun calculateBalances(bills: List<Bill>) {
        bills.forEach { bill ->
            balances.add(Balance(bill.payer, 0))
            bill.debtors.forEach { debtor ->
                balances.add(Balance(debtor, 0))
            }
            balances.find { it.person == bill.payer }!!.amount += bill.amount

            val debtPortion = bill.amount / bill.debtors.size
            assert((bill.amount % bill.debtors.size) <= Int.MAX_VALUE)
            var debtRemainder = (bill.amount % bill.debtors.size).toInt()
            bill.debtors.forEach { debtor ->
                balances.find { it.person == debtor }!!.amount -= debtPortion
            }
            randomOrderedSubset(balances, debtRemainder).forEach { it.amount-- }
        }
    }

    private fun <T> randomOrderedSubset(set: Set<T>, size: Int): List<T> {
        require(size <= set.size)
        val list = set.toList()

        val seed = 0L
        val random = Random(seed)

        val selectedIndices = list.indices
            .shuffled(random)
            .take(size)
            .sorted()
        return selectedIndices.map { list[it] }
    }
}