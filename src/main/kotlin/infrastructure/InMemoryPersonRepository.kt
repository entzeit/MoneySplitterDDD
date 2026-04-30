package main.kotlin.infrastructure

import main.kotlin.domain.model.person.Person
import main.kotlin.domain.model.vo.PersonId
import main.kotlin.domain.model.vo.PersonName
import main.kotlin.domain.repository.PersonRepository

class InMemoryPersonRepository : PersonRepository {
    private val people = mutableMapOf<PersonId, Person>()

    override fun save(person: Person): Person {
        return people.putIfAbsent(person.id, person) ?: person
    }

    override fun findByName(name: PersonName): Person? = people.values.find { it.name() == name }

    override fun findById(id: PersonId): Person? = people[id]
}