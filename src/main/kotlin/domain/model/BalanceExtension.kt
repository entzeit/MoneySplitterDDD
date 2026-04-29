package main.kotlin.domain.model

fun List<Balance>.findByPerson(person: Person): Balance? =
    find { it.person == person }