package main.kotlin

import main.kotlin.application.mapper.TransactionViewMapper
import main.kotlin.application.parser.BillParser
import main.kotlin.application.usecase.CalculateBalancesUseCase
import main.kotlin.application.usecase.CalculateTransactionsUseCase
import main.kotlin.application.usecase.ImportBillsUseCase
import main.kotlin.domain.service.BalanceCalculator
import main.kotlin.domain.service.TransactionCalculator
import main.kotlin.infrastructure.persistence.InMemoryPersonRepository
import main.kotlin.presentation.cli.BillFileReader
import main.kotlin.presentation.cli.TransactionPrinter

fun main(args: Array<String>) {
    if (args.size != 1) {
        println("Usage: java Splitter <file.txt>")
        return
    }
    val fileName = args[0]
    val personRepository = InMemoryPersonRepository()

    //"Uncontrolled user input is passed to Path Traversal sink" -> okay for local CLI tool
    val lines = BillFileReader().read(fileName).getOrElse {
        println("Failed to read file: ${it.message}")
        return
    }

    val bills = ImportBillsUseCase(
        BillParser(),
        personRepository
    ).execute(lines)

    val balances = CalculateBalancesUseCase(
        BalanceCalculator()
    ).execute(bills)

    val transactions = CalculateTransactionsUseCase(
        TransactionCalculator()
    ).execute(balances)

    val transactionViews = TransactionViewMapper(personRepository).map(transactions)
    TransactionPrinter().print(transactionViews)
}