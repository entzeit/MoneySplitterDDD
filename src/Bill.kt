import java.util.UUID

class Bill (
    val payer: Person,
    //todo: >0
    val amount: Long,          // in cents
    //DDD: debtors should never be empty -> making illegal states unrepresentable
    //DDD: Model the Concept, Not Just the Constraint -> ValueObject for things that have constraints
    //todo: valueObject
    val debtors: Debtors
) {
    val id: UUID = UUID.randomUUID() //DDD: important because pointers in databases



    override fun toString(): String {
        return "Payer: $payer\tAmount: $amount\tDebtors: $debtors"
    }
}
