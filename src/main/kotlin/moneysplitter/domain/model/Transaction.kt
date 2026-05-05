package main.kotlin.moneysplitter.domain.model

import main.kotlin.moneysplitter.domain.model.vo.Money
import main.kotlin.moneysplitter.domain.model.vo.PersonId
import main.kotlin.moneysplitter.domain.model.vo.TransactionId

class Transaction constructor(
    val id: TransactionId,
    val from: PersonId,
    val to: PersonId,
    val amount: Money
) {
    companion object {
        fun create(id: TransactionId, from: PersonId, to: PersonId, amount: Money): Transaction {
            require(from != to) { "Cannot transfer to self" }
            require(amount.cents > 0) { "Transaction amount must be positive" }
            return Transaction(id, from, to, amount)
        }
    }

    override fun equals(other: Any?) = other is Transaction && other.id == id
    override fun hashCode() = id.hashCode()
}