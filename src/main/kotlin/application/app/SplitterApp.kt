package main.kotlin.application.app

import main.kotlin.application.mapper.TransactionViewMapper
import main.kotlin.application.service.BillParser
import main.kotlin.application.usecase.CalculateBalancesUseCase
import main.kotlin.application.usecase.CalculateTransactionsUseCase
import main.kotlin.application.usecase.ImportBillsUseCase
import main.kotlin.domain.service.BalanceCalculator
import main.kotlin.domain.service.TransactionCalculator
import main.kotlin.infrastructure.InMemoryPersonRepository
import main.kotlin.`interface`.cli.BillFileReader
import main.kotlin.`interface`.cli.TransactionPrinter

class SplitterApp {

    fun run(fileName: String) {
        val personRepository = InMemoryPersonRepository()
        //val addPersonUseCase = AddPersonUseCase(personRepository)

        val lines = BillFileReader().read(fileName).getOrElse {
            println("Failed to read file: ${it.message}")
            return
        }

        //todo: single source of truth (append-only) -> balances derived from this
        val bills = ImportBillsUseCase(
            BillParser(),
            personRepository
        ).execute(lines)

        //todo: make balances this: Map<PersonId, Long>
        val balances = CalculateBalancesUseCase(
            BalanceCalculator()
        ).execute(bills)

        val transactions = CalculateTransactionsUseCase(
            TransactionCalculator()
        ).execute(balances)

        val transactionViews = TransactionViewMapper(personRepository).map(transactions)
        TransactionPrinter().print(transactionViews)
    }
}