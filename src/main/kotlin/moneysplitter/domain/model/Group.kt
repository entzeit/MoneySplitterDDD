package main.kotlin.moneysplitter.domain.model

import main.kotlin.moneysplitter.domain.model.vo.GroupId
import main.kotlin.moneysplitter.domain.model.vo.PersonId

/**
 * Aggregate Root
 */
class Group private constructor(
    val id: GroupId,
    private val _members: MutableSet<Person> = mutableSetOf(),
    private val _bills: MutableList<Bill> = mutableListOf()
) {
    val members: Set<Person> get() = _members.toSet()
    val bills: List<Bill> get() = _bills.toList()

    fun addMember(person: Person) {
        _members.add(person)
    }

    fun addBill(bill: Bill) {
        require(bill.payer in _members) { "Payer must be a group member" } //todo: ErrorHandling?
        require(bill.debtors.asList().all { it in _members }) { "All debtors must be group members" }
        _bills.add(bill)
    }

    fun isMember(personId: PersonId): Boolean =
        _members.any { it.id == personId }

    companion object {
        fun create(id: GroupId): Group = Group(id)
    }

    override fun equals(other: Any?) = other is Group && other.id == id
    override fun hashCode() = id.hashCode()
}