package main.kotlin.infrastructure

import main.kotlin.domain.model.Person
import main.kotlin.domain.repository.PersonRepository

class InMemoryPersonRepository : PersonRepository {
    private val peopleSet = mutableSetOf<Person>()

    override fun save(person: Person): Person {
        val existing = peopleSet.find { it == person } //todo: enforce in domain logic instead of infrastructure
        return if (existing != null) existing
        else { peopleSet.add(person); person }
    }

    override fun findByName(name: String): Person? = peopleSet.find { it.name.equals(name, true) }
}