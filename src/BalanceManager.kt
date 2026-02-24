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
            var debtTotalRemainder = bill.amount % bill.debtors.size
            bill.debtors.forEach { debtor ->
                val debtRemainder = if (debtTotalRemainder > 0) 1 else 0
                balances.find { it.person == debtor }!!.amount -= (debtPortion + debtRemainder)
                debtTotalRemainder--
            }
        }
    }
}