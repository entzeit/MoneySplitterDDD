package main.kotlin.domain.model.vo

@JvmInline
value class PersonName(val value: String) {
    //validation
    init {
        require(value.isNotBlank()) { "Name cannot be blank" }
        require(value.length <= 100) { "Name is too long" }
        require(regex.matches(value)) { "Invalid name format: $value" }
    }

    //factory for sanitization
    companion object {
        private val regex = Regex("""^\p{L}+( \p{L}+)*$""")

        fun create(raw: String): PersonName {
            val normalized = raw.trim().replace(Regex("\\s+"), " ")
            return PersonName(normalized)
        }
    }

    override fun toString(): String = value
}