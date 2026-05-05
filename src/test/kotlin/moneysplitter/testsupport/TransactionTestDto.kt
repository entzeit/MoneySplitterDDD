package moneysplitter.testsupport

import main.kotlin.moneysplitter.domain.model.vo.Money
import main.kotlin.moneysplitter.domain.model.vo.PersonId
import main.kotlin.moneysplitter.domain.model.vo.TransactionId

data class TransactionTestDto(
    val id: TransactionId,
    val from: PersonId,
    val to: PersonId,
    val amount: Money
)