package com.tygern.permutations


data class Permutation internal constructor(private val digits: List<Int>)

fun createPermutation(vararg digits: Int):Permutation {
    val length = digits.size

    val validDigits = digits
            .distinct()
            .filter { it in 1..length }

    if (validDigits.size != length) {
        throw IllegalPermutationException("invalid permutation")
    }

    return Permutation(digits.asList())
}


class IllegalPermutationException(message: String) : IllegalStateException(message)