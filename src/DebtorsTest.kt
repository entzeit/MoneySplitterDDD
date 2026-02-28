import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class DebtorsTest {

    @Test
    fun `debtors should protect internal state from external list mutation`() {
        val alice = Person("Alice")
        val bob = Person("Bob")
        val mutableList = mutableListOf(alice, bob)

        val debtors = Debtors.of(mutableList)

        // Mutate original list
        mutableList.clear()

        // The internal Debtors state is still intact
        val result = debtors.asList()
        assertEquals(2, result.size)
        assertTrue(result.contains(alice))
        assertTrue(result.contains(bob))
    }
}