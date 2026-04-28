package test.kotlin.domain.model

import main.kotlin.domain.model.Debtors
import main.kotlin.domain.model.Person
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DebtorsTest {

    @Test
    fun `debtors should protect internal state from external list mutation`() {
        val alice = Person("Alice")
        val bob = Person("Bob")
        val mutableList = mutableListOf(alice, bob)

        val debtors = Debtors.Companion.of(mutableList)

        // Mutate original list
        mutableList.clear()

        // The internal main.kotlin.interface.cli.main.kotlin.domain.model.Debtors state is still intact
        val result = debtors.asList()
        Assertions.assertEquals(2, result.size)
        Assertions.assertTrue(result.contains(alice))
        Assertions.assertTrue(result.contains(bob))
    }
}