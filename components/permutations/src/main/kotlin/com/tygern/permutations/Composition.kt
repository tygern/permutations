package com.tygern.permutations


class Composition(vararg candidates: Permutation) {

    val permutations = candidates.asList()
    val order = permutations.first().order

    init {
        if (!permutations.all { it.order == order }) {
            throw IllegalCompositionException("invalid composition")
        }
    }

    fun reduce() = permutations
            .subList(0, permutations.size - 1)
            .reversed()
            .fold(permutations.last(),
                    { result, element -> product(element, result) })

    fun equivalent(other: Composition) = this.reduce() == other.reduce()


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Composition

        if (permutations != other.permutations) return false
        if (order != other.order) return false

        return true
    }

    override fun hashCode(): Int {
        var result = permutations.hashCode()
        result = 31 * result + order
        return result
    }

    private fun product(left: Permutation, right: Permutation): Permutation {
        val newDigits = (1..right.order).map { element -> right.mapsFrom(left.mapsFrom(element)) }

        return Permutation(*newDigits.toIntArray())
    }
}

class IllegalCompositionException(message: String) : IllegalStateException(message)
