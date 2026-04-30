package main.kotlin.domain.model

import main.kotlin.domain.model.vo.Money
import main.kotlin.domain.model.vo.PersonId
import java.util.*

class Transaction(val from: PersonId, val to: PersonId, val amount: Money) {
    val id: UUID = UUID.randomUUID() //todo: TransactionID
}
