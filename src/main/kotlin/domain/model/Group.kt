package main.kotlin.domain.model

import main.kotlin.application.usecase.CalculateBalancesUseCase
import main.kotlin.domain.service.BalanceCalculator
import main.kotlin.domain.service.BillFactory
import main.kotlin.domain.service.BillManager
import main.kotlin.domain.service.PersonRegistry
import main.kotlin.domain.service.TransactionManager
import java.util.UUID

/**
 * Aggregate Root
 */
class Group(args: Array<String>) { //todo: more constraint
    val id: UUID = UUID.randomUUID()

    init {
        val personRegistry = PersonRegistry()
        val billFactory = BillFactory(personRegistry)
        val billManager = BillManager(billFactory.fromFile(args[0]))
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