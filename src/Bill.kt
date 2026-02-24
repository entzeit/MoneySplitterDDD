import java.util.UUID

class Bill(
    val payer: Person,
    val amount: Long,          // in cents
    val debtors: List<Person>
) {
    val id: UUID = UUID.randomUUID()

    override fun toString(): String {
        return "Payer: $payer\tAmount: $amount\tDebtors: $debtors"
    }
}
