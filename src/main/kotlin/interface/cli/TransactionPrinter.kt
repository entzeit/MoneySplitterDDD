package main.kotlin.`interface`.cli

import main.kotlin.`interface`.cli.view.TransactionView

class TransactionPrinter {
    fun print(transactions: List<TransactionView>) {
        transactions.forEach {
            println("${it.fromName} -> ${it.toName}: ${it.amount}")
        }
    }
}