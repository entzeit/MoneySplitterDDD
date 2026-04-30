package main.kotlin.domain.model

import main.kotlin.domain.model.vo.Money
import main.kotlin.domain.model.vo.PersonId

//Derived read model / projection of "Balance" Value Object
@JvmInline
value class Balances(private val value: Map<PersonId, Money>) {

    operator fun get(id: PersonId): Money =
        value[id] ?: Money.ZERO

    fun toMap(): Map<PersonId, Money> = value
}