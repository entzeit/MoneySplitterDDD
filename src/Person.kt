import java.util.UUID

class Person (var name: String) { //todo: Private constructor + Factory method in Registry
    val id: UUID = UUID.randomUUID()

    override fun toString(): String {
        return name
    }

    override fun equals(other: Any?) = other is Person && other.name == name
    override fun hashCode() = id.hashCode()
}