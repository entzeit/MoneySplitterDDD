package main.kotlin.`interface`.cli

import main.kotlin.domain.model.Transaction

class TransactionPrinter {

    fun print(transactions: List<Transaction>) {
        transactions.forEach {
            println("${it.from} -> ${it.to}: ${it.amount.formatToEuro()}")
        }
    }
}