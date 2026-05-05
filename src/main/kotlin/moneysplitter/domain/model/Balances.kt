package main.kotlin.moneysplitter.domain.model

import main.kotlin.moneysplitter.domain.model.vo.Money
import main.kotlin.moneysplitter.domain.model.vo.PersonId

//Derived read model / projection of "Balance" Value Object
@JvmInline
value class Balances(private val value: Map<PersonId, Money>) {

    operator fun get(id: PersonId): Money =
        value[id] ?: Money.ZERO

    fun toMap(): Map<PersonId, Money> = value.toMap()
}