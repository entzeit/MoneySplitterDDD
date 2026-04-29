package main.kotlin.domain.model

import main.kotlin.domain.model.person.Person

fun List<Balance>.findByPerson(person: Person): Balance? =
    find { it.person == person }