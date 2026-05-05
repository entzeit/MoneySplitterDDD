package main.kotlin.moneysplitter

import main.kotlin.moneysplitter.presentation.mapper.TransactionViewMapper
import main.kotlin.moneysplitter.application.parser.BillParser
import main.kotlin.moneysplitter.application.usecase.CalculateBalancesUseCase
import main.kotlin.moneysplitter.application.usecase.CalculateTransactionsUseCase
import main.kotlin.moneysplitter.application.usecase.ImportBillsUseCase
import main.kotlin.moneysplitter.domain.service.BalanceCalculator
import main.kotlin.moneysplitter.domain.service.TransactionCalculator
import main.kotlin.moneysplitter.infrastructure.persistence.InMemoryPersonRepository
import main.kotlin.moneysplitter.presentation.cli.BillFileReader
import main.kotlin.moneysplitter.presentation.cli.TransactionPrinter

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