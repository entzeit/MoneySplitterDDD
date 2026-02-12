fun main(args: Array<String>) {
    if (args.size != 1) {
        println("Usage: java Splitter <file.txt>")
        return
    }
    val manager = BillManager.fromFile(args[0])
    manager.printBills()
}