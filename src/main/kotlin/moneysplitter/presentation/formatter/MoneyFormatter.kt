package main.kotlin.moneysplitter.presentation.formatter

import main.kotlin.moneysplitter.domain.model.vo.Money

object MoneyFormatter {
    fun toEuro(money: Money): String {
        val euro = money.cents / 100
        val centPart = kotlin.math.abs(money.cents % 100)
            .toString()
            .padStart(2, '0')
        return "$euro,$centPart€"
    }
}