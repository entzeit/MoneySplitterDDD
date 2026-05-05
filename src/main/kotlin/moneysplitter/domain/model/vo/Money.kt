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

    override fun equals(other: Any?): Boolean =
        other is Money && cents == other.cents

    override fun hashCode(): Int =
        cents.hashCode()

    override fun toString(): String = //for debugging only
        "Money(cents=$cents)"
}