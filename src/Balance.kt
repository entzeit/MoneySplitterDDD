class Balance(val person: Person, var amount: Int) {
    override fun equals(other: Any?) = other is Balance && person == other.person
    override fun hashCode() = person.hashCode()
    override fun toString(): String {
        return "$person\t$amount"
    }
}