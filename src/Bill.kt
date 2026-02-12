data class Bill(
    val payer: String,
    val amount: Int,          // in cents
    val debtors: List<String>
) {
    companion object {
        fun fromLine(line: String): Bill {
            val parts = line.split(" ")
            val payer = parts[0].lowercase()
            val amount = toCents(parts[1])
            val debtors = parts[2].split(",").map { it.trim().lowercase() }
            return Bill(payer, amount, debtors)
        }

        private fun toCents(amount: String): Int {
            val parts = amount.split(",")
            val euros = parts[0].toInt()
            val cents = if (parts.size == 2) {
                if (parts[1].length == 1) parts[1].toInt() * 10 else parts[1].toInt()
            } else 0
            return euros * 100 + cents
        }
    }

    override fun toString(): String {
        return "$payer $amount $debtors"
    }
}
