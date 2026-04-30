package main.kotlin.domain.repository

import main.kotlin.domain.model.Person
import main.kotlin.domain.model.vo.PersonId
import main.kotlin.domain.model.vo.PersonName

interface PersonRepository {
    fun save(person: Person): Person
    fun findByName(name: PersonName): Person?
    fun findById(id: PersonId): Person?
}