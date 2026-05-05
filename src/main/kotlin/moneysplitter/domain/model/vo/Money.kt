package main.kotlin.moneysplitter.domain.model.vo

class Money private constructor (
    val cents: Long,
    //todo: val currency: Currency
) : Comparable<Money> {
    companion object {
        fun ofCents(cents: Long) =
            Money(cents)

        val ZERO: Money = Money(0L)
    }

    fun abs(): Money = Money(kotlin.math.abs(cents))

    override fun compareTo(other: Money): Int =
        cents.compareTo(other.cents)

    operator fun plus(other: Money): Money {
        return Money(Math.addExact(this.cents, other.cents))
    }

    operator fun minus(other: Money): Money {
        return Money(Math.subtractExact(this.cents, other.cents))
    }

    fun formatToEuro(): String {
        val euro = cents / 100
        val centPart = kotlin.math.abs(cents % 100)
            .toString()
            .padStart(2, '0')
        return "$euro,$centPart"
    }

    override fun equals(other: Any?): Boolean =
        other is Money && cents == other.cents

    override fun hashCode(): Int =
        cents.hashCode()

    override fun toString(): String = //for debugging only
        "Money(cents=$cents)"
}