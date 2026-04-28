package main.kotlin.domain.model

class Balance(val person: Person, var amount: Long) {
    override fun equals(other: Any?) = other is Balance && person == other.person
    override fun hashCode() = person.hashCode()
    override fun toString(): String {
        return "$person\t$amount"
    }
}