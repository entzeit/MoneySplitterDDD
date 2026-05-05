package main.kotlin.moneysplitter.domain.model

import main.kotlin.moneysplitter.domain.model.vo.Money
import main.kotlin.moneysplitter.domain.model.vo.PersonId
import main.kotlin.moneysplitter.domain.model.vo.TransactionId

class Transaction(val id: TransactionId, val from: PersonId, val to: PersonId, val amount: Money)