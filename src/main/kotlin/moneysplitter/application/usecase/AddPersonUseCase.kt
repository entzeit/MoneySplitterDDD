package main.kotlin.moneysplitter.application.usecase

import main.kotlin.moneysplitter.domain.model.Person
import main.kotlin.moneysplitter.domain.model.vo.PersonId
import main.kotlin.moneysplitter.domain.model.vo.PersonName
import main.kotlin.moneysplitter.domain.repository.PersonRepository

class AddPersonUseCase(
    private val personRepository: PersonRepository
) {
    fun execute(name: PersonName): Person {
        val person =
            Person(PersonId.new(), name) //Application owns identity -> no hidden randomness -> easy to test
        return personRepository.save(person)
    }
}