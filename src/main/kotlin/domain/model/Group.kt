package main.kotlin.domain.model

import main.kotlin.application.service.BillParser
import main.kotlin.application.usecase.AddPersonUseCase
import main.kotlin.application.usecase.CalculateBalancesUseCase
import main.kotlin.application.usecase.ImportBillsUseCase
import main.kotlin.domain.service.BalanceCalculator
import main.kotlin.domain.service.BillManager
import main.kotlin.domain.service.TransactionManager
import main.kotlin.infrastructure.InMemoryPersonRepository
import main.kotlin.`interface`.cli.BillFileReader
import java.util.UUID

/**
 * Aggregate Root
 */
class Group(args: Array<String>) { //todo: more constraint
    val id: UUID = UUID.randomUUID()

    init {
        val personRepository = InMemoryPersonRepository()
        val addPersonUseCase = AddPersonUseCase(personRepository)
        val lines = BillFileReader().read(args[0]).getOrElse {
            println("Failed to read file: ${it.message}")
            emptyList() //todo: return?
        }
        val importBillsUseCase =
            ImportBillsUseCase(BillParser(), personRepository)
        val bills = importBillsUseCase.execute(lines)
        val billManager = BillManager(bills)
        val calculator = BalanceCalculator()
        val calculateBalancesUseCase = CalculateBalancesUseCase(calculator)
        val balances = calculateBalancesUseCase.execute(billManager.bills)
        val transactionManager = TransactionManager(balances)
        transactionManager.calculateTransactions()
        println(transactionManager)
    }
}

//todo:
/**make it better with more constraints and functions. Like how I would do it in an app!
 *
 * example:
 * class main.kotlin.interface.cli.main.kotlin.domain.model.Group(
 *     val id: GroupId
 * ) {
 *     private val members = mutableMapOf<PersonId, main.kotlin.interface.cli.main.kotlin.domain.model.Person>()
 *     private val bills = mutableListOf<main.kotlin.interface.cli.main.kotlin.domain.model.Bill>()
 *
 *     fun addMember(person: main.kotlin.interface.cli.main.kotlin.domain.model.Person) { ... }
 *
 *     fun addBill(bill: main.kotlin.interface.cli.main.kotlin.domain.model.Bill) { ... }
 *
 *     fun balances(): Map<PersonId, main.kotlin.interface.cli.main.kotlin.domain.model.Balance> { ... }
 *
 *     fun settlementTransactions(): List<main.kotlin.interface.cli.main.kotlin.domain.model.Transaction> { ... }
 * }
 */