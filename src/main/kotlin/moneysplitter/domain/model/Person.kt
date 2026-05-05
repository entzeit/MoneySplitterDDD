package main.kotlin.moneysplitter.domain.model

import main.kotlin.moneysplitter.domain.model.vo.PersonId
import main.kotlin.moneysplitter.domain.model.vo.PersonName

//Entity
class Person (
    val id: PersonId,
    private var name: PersonName
) {
    fun name(): PersonName = name
    fun changeName(newName: PersonName) {
        name = newName
    }
    override fun toString() = name.value
    override fun equals(other: Any?) = other is Person && other.id == id
    override fun hashCode() = id.hashCode()
}