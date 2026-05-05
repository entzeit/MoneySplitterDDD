package main.kotlin.moneysplitter.domain.repository

import main.kotlin.moneysplitter.domain.model.Person
import main.kotlin.moneysplitter.domain.model.vo.PersonId
import main.kotlin.moneysplitter.domain.model.vo.PersonName

interface PersonRepository {
    fun save(person: Person): Person
    fun findByName(name: PersonName): Person?
    fun findById(id: PersonId): Person?
}