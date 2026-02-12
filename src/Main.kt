fun main(args: Array<String>) {
    if (args.size != 1) {
        println("Usage: java Splitter <file.txt>")
        return
    }
    val billManager = BillManager(args[0])
    //billManager.bills.forEach { println(it) }
}