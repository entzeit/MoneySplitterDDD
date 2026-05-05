package main.kotlin.moneysplitter.domain.model.vo

import main.kotlin.moneysplitter.domain.model.Person

//Value Object
class Debtors private constructor(
    private val people: List<Person>
) {
    init {
        // Invariant enforcement (DDD: ValueObject must always be valid)
        require(this.people.isNotEmpty()) { "Debtors cannot be empty." }
    }

    val size: Int
        get() = people.size

    /**
     * Why .toList()?
     * Defensive copy to ensure the Debtors object owns its state. Debtors owns its list now.
     *
     * Why isn't List<Person> instead of MutableList<Person> not preventing from constructing with a mutable list?
     * The type readOnly only exposes read-only methods.
     * But the underlying object is still mutable.
     * If someone else still has a reference to the original mutable list, they can mutate it.
     * read-only interface ≠ immutability.
     *
     * It's a shallow copy and it prevents changing the original list's structure but it can change the referenced objects inside
     */
    fun asList(): List<Person> = people.toList()

    /**
     * Avoiding Raw Collection Exposure:
     */
    fun contains(person: Person): Boolean = people.contains(person)

    /** Map over debtors safely */
    fun <R> map(transform: (Person) -> R): List<R> = people.map(transform)

    fun forEach(action: (Person) -> Unit) = people.forEach(action)

    companion object {
        /** Factory method to create Debtors with a defensive copy */
        fun of(people: List<Person>): Debtors {
            return Debtors(people.toList())
        }
    }

    /**
     * Domain logic implemented in equals and hashCode:
     * Order doesn't matter
     * -> that's why data class is misleading and better use class instead
     */
    override fun equals(other: Any?) =
        other is Debtors && people.toSet() == other.people.toSet()

    override fun hashCode() = people.toSet().hashCode()
}