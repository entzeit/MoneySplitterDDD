package main.kotlin.moneysplitter.domain.model.vo

import java.util.*

@JvmInline
value class GroupId(val value: UUID) {
    companion object {
        fun new(): GroupId = GroupId(UUID.randomUUID())
    }
}