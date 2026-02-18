import java.util.UUID

class Transaction(val from: Person, val to: Person, val amount: Int) {
    val id: UUID = UUID.randomUUID()
}
