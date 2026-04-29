package main.kotlin.domain.model

import java.util.*

/**
 * Aggregate Root
 */
class Group(args: Array<String>) { //todo: more constraint
    val id: UUID = UUID.randomUUID()
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