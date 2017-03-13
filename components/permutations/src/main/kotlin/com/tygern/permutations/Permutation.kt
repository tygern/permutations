package com.tygern.permutations


data class Permutation internal constructor(private val digits: List<Int>) {
    fun order() = digits.size
    fun goesTo(i: Int) = digits.indexOf(i) + 1
    fun inverse(): Permutation {
        val resultDigits = IntArray(order())

        digits.forEachIndexed { index, digit ->
            resultDigits[digit - 1] = index + 1
        }

        return Permutation(resultDigits.toList())
    }
}

fun p(vararg digits: Int): Permutation {
    val length = digits.size

    val validDigits = digits
            .distinct()
            .filter { it in 1..length }

    if (validDigits.size != length) {
        throw IllegalPermutationException("invalid permutation")
    }

    return Permutation(digits.asList())
}

fun compose(left: Permutation, right: Permutation): Permutation {
    if (left.order() != right.order()) {
        throw IllegalOperationException("invalid operation")
    }

    val order = right.order()
    val resultDigits = IntArray(order)

    (1..order).forEach {
        resultDigits[left.goesTo(right.goesTo(it)) - 1] = it
    }


    return Permutation(resultDigits.toList())
}

class IllegalPermutationException(message: String) : IllegalStateException(message)
class IllegalOperationException(message: String) : IllegalStateException(message)