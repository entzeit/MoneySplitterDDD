import java.util.UUID

/**
 * Aggregate Root
 */
class Group(args: Array<String>) { //todo: more constraint
    val id: UUID = UUID.randomUUID()

    init {
        val personRegistry = PersonRegistry()
        val billFactory = BillFactory(personRegistry)
        val billManager = BillManager(billFactory.fromFile(args[0]))
        val balanceManager = BalanceManager()
        balanceManager.calculateBalances(billManager.bills)
        val transactionManager = TransactionManager(balanceManager)
        transactionManager.calculateTransactions()
        println(transactionManager)
    }
}

//todo:
/**make it better with more constraints and functions. Like how I would do it in an app!
 *
 * example:
 * class Group(
 *     val id: GroupId
 * ) {
 *     private val members = mutableMapOf<PersonId, Person>()
 *     private val bills = mutableListOf<Bill>()
 *
 *     fun addMember(person: Person) { ... }
 *
 *     fun addBill(bill: Bill) { ... }
 *
 *     fun balances(): Map<PersonId, Balance> { ... }
 *
 *     fun settlementTransactions(): List<Transaction> { ... }
 * }
 */