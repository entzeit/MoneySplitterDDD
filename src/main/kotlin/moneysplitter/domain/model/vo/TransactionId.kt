package main.kotlin.moneysplitter.domain.model.vo

import java.util.*

@JvmInline
value class TransactionId(val value: UUID) {
    companion object {
        fun new(): TransactionId = TransactionId(UUID.randomUUID())
    }
}