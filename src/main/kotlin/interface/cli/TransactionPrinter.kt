package main.kotlin.`interface`.cli

import main.kotlin.domain.model.Transaction

class TransactionPrinter {

    fun print(transactions: List<Transaction>) {
        transactions.forEach {
            println("${it.from} -> ${it.to}: ${toEuro(it.amount)}")
        }
    }

    private fun toEuro(amount: Long): String {
        val euro = amount / 100
        val cents = (amount % 100).let { if (it < 10) "0$it" else "$it" }
        return "$euro,$cents"
    }
}