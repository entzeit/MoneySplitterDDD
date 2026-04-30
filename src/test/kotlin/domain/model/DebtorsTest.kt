package test.kotlin.domain.model

import main.kotlin.domain.model.Debtors
import main.kotlin.domain.model.person.Person
import main.kotlin.domain.model.vo.PersonId
import main.kotlin.domain.model.vo.PersonName
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DebtorsTest {

    @Test
    fun `debtors should protect internal state from external list mutation`() {
        val alice = Person(PersonId.new(), PersonName("Alice"))
        val bob = Person(PersonId.new(), PersonName("Bob"))
        val mutableList = mutableListOf(alice, bob)

        val debtors = Debtors.of(mutableList)

        // Mutate original list
        mutableList.clear()

        // The internal Debtors state is still intact
        val result = debtors.asList()
        Assertions.assertEquals(2, result.size)
        Assertions.assertTrue(result.contains(alice))
        Assertions.assertTrue(result.contains(bob))
    }
}