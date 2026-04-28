package main.kotlin.domain.model

import java.util.UUID

class Person (var name: String) {
    val id: UUID = UUID.randomUUID()
    //var balance: main.kotlin.interface.cli.main.kotlin.domain.model.Balance = main.kotlin.interface.cli.main.kotlin.domain.model.Balance()

    override fun toString(): String {
        return name
    }

    override fun equals(other: Any?) = other is Person && other.id == id
    override fun hashCode() = id.hashCode()
}