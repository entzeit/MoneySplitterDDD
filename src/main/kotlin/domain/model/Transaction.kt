package main.kotlin.domain.model

import main.kotlin.domain.model.vo.Money
import main.kotlin.domain.model.vo.PersonId
import main.kotlin.domain.model.vo.TransactionId

class Transaction(val id: TransactionId, val from: PersonId, val to: PersonId, val amount: Money)