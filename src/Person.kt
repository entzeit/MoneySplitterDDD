import java.util.UUID

class Person (var name: String) {
    val id: UUID = UUID.randomUUID()

    override fun toString(): String {
        return name
    }

    override fun equals(other: Any?) = other is Person && other.id == id
    override fun hashCode() = id.hashCode()
}