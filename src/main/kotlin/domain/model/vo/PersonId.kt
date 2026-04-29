package main.kotlin.domain.model.vo

import java.util.*

/*
Value Object:
- without normalization, multiple fields, derived properties, internal consistency rule
 */
@JvmInline
value class PersonId(val value: UUID) {
    companion object {
        fun new(): PersonId = PersonId(UUID.randomUUID())
    }
}