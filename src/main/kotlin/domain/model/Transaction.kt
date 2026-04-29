package main.kotlin.domain.model

import main.kotlin.domain.model.person.Person
import main.kotlin.domain.model.vo.Money
import java.util.UUID

class Transaction(val from: Person, val to: Person, val amount: Money) {
    val id: UUID = UUID.randomUUID()
}
