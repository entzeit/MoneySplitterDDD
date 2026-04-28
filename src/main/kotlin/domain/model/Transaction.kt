package main.kotlin.domain.model

import java.util.UUID

class Transaction(val from: Person, val to: Person, val amount: Long) {
    val id: UUID = UUID.randomUUID()
}
