class Bill(
    val payer: String,
    amountStr: String,
    debtorsStr: String
) {
    val amount: Int = toCents(amountStr)          // amount in cents
    val debtors: List<String> = debtorsStr.split(",").map { it.trim() }  // clean list

    private fun toCents(amount: String): Int { //expects 12,3 or 12,34 format
        val parts = amount.split(",")
        val euros = parts[0].toInt()
        var cents = 0
        if (parts.size == 2) {
            cents = if (parts[1].length == 1) parts[1].toInt() * 10
            else parts[1].toInt()
        }
        return euros * 100 + cents
    }

    override fun toString(): String {
        return "Payer: $payer\t Amount: $amount\t Debtors: $debtors"
    }
}
