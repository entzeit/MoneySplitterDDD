package main.kotlin.presentation.cli

import main.kotlin.presentation.view.TransactionView

class TransactionPrinter {
    fun print(transactions: List<TransactionView>) {
        transactions.forEach {
            println("${it.fromName} -> ${it.toName}: ${it.amount}")
        }
    }
}