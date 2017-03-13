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

    fun inverse() = Permutation(*(1..order).map { goesTo(it) }.toIntArray())

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

class IllegalPermutationException(message: String) : IllegalStateException(message)
