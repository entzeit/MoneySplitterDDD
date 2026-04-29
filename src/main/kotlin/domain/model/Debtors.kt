package main.kotlin.domain.model

import main.kotlin.domain.model.person.Person

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

/**
 * Model the Concept, Not Just the Constraint
 * ✔ If the rule is simple and local
 * Keep require(debtors.isNotEmpty()) inside Bill.
 * ✔ If the concept has behavior
 * Create a Value Object.
 * Example:
 * If later you need:
 * Split calculation
 * Remove debtor
 * Add debtor
 * Equal shares vs weighted shares
 * Then Debtors absolutely deserves to be a Value Object.
 *
 * Why no data class:
 * no copy(), no wrong equals() or hashCode()
 * better use class instead of data class because data class creates copy() which can bypass init requirements and create invariants
 */