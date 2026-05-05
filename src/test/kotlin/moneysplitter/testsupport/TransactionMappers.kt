package moneysplitter.testsupport

import main.kotlin.moneysplitter.domain.model.Transaction

fun Transaction.toTestDto() = TransactionTestDto(
    id = id,
    from = from,
    to = to,
    amount = amount
)