package main.kotlin.domain.model

import main.kotlin.domain.model.vo.BillId
import main.kotlin.domain.model.vo.Money

class Bill (
    val id: BillId,
    val payer: Person,
    //todo: >0
    val amount: Money,
    //DDD: debtors should never be empty -> making illegal states unrepresentable
    //DDD: Model the Concept, Not Just the Constraint -> ValueObject for things that have constraints
    //todo: valueObject
    val debtors: Debtors
) {
    companion object {
        fun create(
            id: BillId,
            payer: Person,
            amount: Money,
            debtors: List<Person>
        ): Bill {
            require(debtors.isNotEmpty())
            return Bill(BillId.new(), payer, amount, Debtors.of(debtors))
        }
    }

    override fun toString(): String {
        return "Payer: $payer\tAmount: $amount\tDebtors: $debtors"
    }
}
