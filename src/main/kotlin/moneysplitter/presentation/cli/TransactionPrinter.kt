package main.kotlin.moneysplitter.presentation.cli

import main.kotlin.moneysplitter.presentation.view.TransactionView

class TransactionPrinter {
    fun print(transactions: List<TransactionView>) {
        transactions.forEach {
            println("${it.fromName} -> ${it.toName}: ${it.amount}")
        }
    }
}