package main.kotlin.application.usecase

import main.kotlin.domain.model.Person
import main.kotlin.domain.repository.PersonRepository

class AddPersonUseCase(
    private val personRepository: PersonRepository
) {
    fun execute(name: String): Person {
        val person = Person(name)
        return personRepository.save(person)
    }
}