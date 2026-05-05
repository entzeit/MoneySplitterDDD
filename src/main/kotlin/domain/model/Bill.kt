package main.kotlin.domain.model

import main.kotlin.domain.model.vo.Money
import java.util.*

class Bill (
    val payer: Person,
    //todo: >0
    val amount: Money,
    //DDD: debtors should never be empty -> making illegal states unrepresentable
    //DDD: Model the Concept, Not Just the Constraint -> ValueObject for things that have constraints
    //todo: valueObject
    val debtors: Debtors
) {
    val id: UUID = UUID.randomUUID() //DDD: important because pointers in databases

    companion object {
        fun create(
            payer: Person,
            amount: Long,
            debtors: List<Person>
        ): Bill {
            require(debtors.isNotEmpty())
            return Bill(payer, Money.ofCents(amount), Debtors.of(debtors))
        }
    }

    override fun toString(): String {
        return "Payer: $payer\tAmount: $amount\tmain.kotlin.interface.cli.main.kotlin.domain.model.Debtors: $debtors"
    }
}
