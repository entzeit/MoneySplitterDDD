package main.kotlin.domain.model

import main.kotlin.domain.model.person.Person
import main.kotlin.domain.model.vo.Money

class Balance(val person: Person, var amount: Money = Money(0)) {
    override fun equals(other: Any?) = other is Balance && person == other.person
    override fun hashCode() = person.hashCode()
    override fun toString(): String {
        return "$person\t$amount"
    }
}