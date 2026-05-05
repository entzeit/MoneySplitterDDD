package main.kotlin.application.dto
import main.kotlin.domain.model.vo.Money
import main.kotlin.domain.model.vo.PersonName

data class ParsedBill( //clean, but not domain-safe
    val payerName: PersonName,
    val amount: Money,
    val debtorNames: List<PersonName>
)