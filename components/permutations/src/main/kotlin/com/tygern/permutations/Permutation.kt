package com.tygern.permutations

class Permutation(vararg numbers: Int) {

    val digits = numbers.asList()
    val order = digits.size

    init {
        val numberOfValidDigits = digits
                .distinct()
                .count { it in 1..order }

        if (numberOfValidDigits != order) {
            throw IllegalPermutationException("invalid permutation")
        }
    }

    fun goesTo(i: Int) = digits.indexOf(i) + 1
    fun mapsFrom(i: Int) = digits[i - 1]

    fun inverse() = IntArray(order).let {
        digits.forEachIndexed { index, digit ->
            it[digit - 1] = index + 1
        }

        Permutation(*it)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Permutation

        if (digits != other.digits) return false

        return true
    }

    override fun hashCode() = digits.hashCode()

    override fun toString() = "Permutation$digits"
}

fun compose(left: Permutation, right: Permutation):Permutation {
    if (left.order != right.order) {
        throw IllegalOperationException("invalid operation")
    }

    val map = (1..right.order).map { element -> right.mapsFrom(left.mapsFrom(element)) }

    return Permutation(*map.toIntArray())
}

class IllegalPermutationException(message: String) : IllegalStateException(message)
class IllegalOperationException(message: String) : IllegalStateException(message)
