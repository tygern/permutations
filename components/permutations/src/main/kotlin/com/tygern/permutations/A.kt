package com.tygern.permutations


class A(val order: Int) {
    val identity = Permutation(*1.rangeTo(order + 1).toList().toIntArray())

    fun s(i: Int): Permutation {
        if (!validGenerator(i)) {
            throw GroupOrderException("Generator index must be between 1 and $order")
        }

        val digits = 1.rangeTo(order + 1).toList().toIntArray().apply {
            this[i - 1] = i + 1
            this[i] = i
        }

        return Permutation(*digits)
    }


    fun element(vararg generators: Int)
        = Composition(*generators.map { s(it) }.toTypedArray())
        .reduce()

    private fun validGenerator(i: Int) = 1.rangeTo(order).contains(i)
}

class GroupOrderException(message: String) : IllegalStateException(message)
