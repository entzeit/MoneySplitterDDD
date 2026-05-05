package main.kotlin.moneysplitter.domain.model

import main.kotlin.moneysplitter.domain.model.vo.BillId
import main.kotlin.moneysplitter.domain.model.vo.Debtors
import main.kotlin.moneysplitter.domain.model.vo.Money

class Bill (
    val id: BillId,
    val payer: Person,
    val amount: Money,
    val debtors: Debtors
) {
    companion object { //Factory
        fun create(
            id: BillId,
            payer: Person,
            amount: Money,
            debtors: List<Person>
        ): Bill {
            require(amount.cents > 0) { "Amount must be positive in Bill" }
            return Bill(id, payer, amount, Debtors.of(debtors))
        }
    }

    override fun toString(): String {
        return "Payer: $payer\tAmount: $amount\tDebtors: $debtors"
    }

    override fun equals(other: Any?) = other is Bill && other.id == id
    override fun hashCode() = id.hashCode()
}
