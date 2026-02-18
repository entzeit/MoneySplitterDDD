fun main(args: Array<String>) {
    if (args.size != 1) {
        println("Usage: java Splitter <file.txt>")
        return
    }
    val personRegistry = PersonRegistry()
    val billFactory = BillFactory(personRegistry)
    val billManager = BillManager(billFactory.fromFile(args[0]))
    val balanceManager = BalanceManager()
    balanceManager.calculateBalances(billManager.bills)
    val transactionManager = TransactionManager(balanceManager)
    transactionManager.calculateTransactions()
    println(transactionManager)
}