class BalanceManager {
    val balances: MutableSet<Balance> = mutableSetOf() //doesn't add if already exists
    fun calculateBalances(bills: List<Bill>) {
        //todo: improve by using a temporary map
        //val debtMap = mutableMapOf<Person, Debt>()
        /*
        Why is a map faster? In the Set I check for the person id and in the Map I check for hashcode.
         */
        bills.forEach { bill ->
            balances.add(Balance(bill.payer, 0))
            bill.debtors.forEach { debtor ->
                balances.add(Balance(debtor, 0))
            }
            val debt: Int = (bill.amount / bill.debtors.size) * bill.debtors.size //workaround for non dividable debts
            balances.find { it.person == bill.payer }!!.amount += debt
            bill.debtors.forEach { debtor ->
                val debtProportion = bill.amount / bill.debtors.size
                balances.find { it.person == debtor }!!.amount -= debtProportion
            }
        }
    }
}