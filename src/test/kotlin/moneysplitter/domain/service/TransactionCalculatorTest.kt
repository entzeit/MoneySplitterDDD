// src/test/kotlin/moneysplitter/domain/service/TransactionCalculatorTest.kt
package moneysplitter.domain.service

import main.kotlin.moneysplitter.domain.model.Balances
import main.kotlin.moneysplitter.domain.model.Transaction
import main.kotlin.moneysplitter.domain.model.vo.Money
import main.kotlin.moneysplitter.domain.model.vo.PersonId
import main.kotlin.moneysplitter.domain.model.vo.TransactionId
import main.kotlin.moneysplitter.domain.service.TransactionCalculator
import moneysplitter.testsupport.toTestDto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

class TransactionCalculatorTest {

    private lateinit var calculator: TransactionCalculator

    // Fixed UUIDs for deterministic tests
    private val alice = PersonId(UUID.fromString("00000000-0000-0000-0000-000000000001"))
    private val bob = PersonId(UUID.fromString("00000000-0000-0000-0000-000000000002"))
    private val charlie = PersonId(UUID.fromString("00000000-0000-0000-0000-000000000003"))

    @BeforeEach
    fun setUp() {
        calculator = TransactionCalculator()
    }

    @Test
    fun `should create single settlement when one debtor and one creditor`() {
        val balances = Balances(mapOf(
            alice to Money.ofCents(-100),
            bob to Money.ofCents(100)
        ))
        val txId = TransactionId(UUID.fromString("00000000-0000-0000-0000-000000000099"))

        val result = calculator.calculate(balances) { txId }

        assertEquals(1, result.size)
        assertEquals(Transaction(txId, alice, bob, Money.ofCents(100)).toTestDto(), result[0].toTestDto())
    }

    @Test
    fun `should return empty list when all balances are zero`() {
        val balances = Balances(mapOf(
            alice to Money.ZERO,
            bob to Money.ZERO
        ))

        val result = calculator.calculate(balances)

        assertEquals(emptyList<Transaction>(), result)
    }

    @Test
    fun `should handle multiple debtors and creditors`() {
        val balances = Balances(mapOf(
            alice to Money.ofCents(-50),
            bob to Money.ofCents(-50),
            charlie to Money.ofCents(100)
        ))
        var idCounter = 0
        val idProvider = { TransactionId(UUID(0, (++idCounter).toLong())) }

        val result = calculator.calculate(balances, idProvider)

        assertEquals(2, result.size)
        assertEquals(Money.ofCents(100), result.sumOf { it.amount.cents }.let { Money.ofCents(it) })
    }
}