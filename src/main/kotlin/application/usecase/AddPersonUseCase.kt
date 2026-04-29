package main.kotlin.application.usecase

import main.kotlin.domain.model.person.Person
import main.kotlin.domain.model.vo.PersonId
import main.kotlin.domain.model.vo.PersonName
import main.kotlin.domain.repository.PersonRepository

class AddPersonUseCase(
    private val personRepository: PersonRepository
) {
    fun execute(name: PersonName): Person {
        val person = Person(PersonId.new(), name) //Application owns identity -> no hidden randomness -> easy to test
        return personRepository.save(person)
    }
}