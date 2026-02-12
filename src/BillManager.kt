class BillManager(val bills: List<Bill>) {
    fun printBills() {
        bills.forEach { println(it) }
    }

    companion object {
        fun fromFile(fileName: String): BillManager {
            val bills = try {
                java.io.File(fileName).readLines().map { line -> Bill.fromLine(line) }
            } catch (e: Exception) {
                println("Failed to read file: ${e.message}")
                emptyList()
            }
            return BillManager(bills)
        }
    }
}
