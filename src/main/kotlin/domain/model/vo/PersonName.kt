package main.kotlin.domain.model.vo

/*
Value Object:
- without normalization, multiple fields, derived properties, internal consistency rule
 */
@JvmInline
value class PersonName(val value: String) {
    init {
        require(value.isNotBlank()) { "Name cannot be blank" }
        require(value.length <= 100) { "Name is too long (max 100)" }
    }
    override fun toString(): String = value
}