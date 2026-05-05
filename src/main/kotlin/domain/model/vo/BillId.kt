package main.kotlin.domain.model.vo

import java.util.*

@JvmInline
value class BillId(val value: UUID) {
    companion object {
        fun new(): BillId = BillId(UUID.randomUUID())
    }
}