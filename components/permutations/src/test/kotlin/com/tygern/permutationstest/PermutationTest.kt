package com.tygern.permutationstest

import com.tygern.permutations.IllegalPermutationException
import com.tygern.permutations.Permutation
import com.tygern.permutations.createPermutation
import io.damo.aspen.Test
import io.damo.aspen.expectException
import org.assertj.core.api.Assertions.assertThat


class PermutationTest: Test({
    describe("#createPermutation") {
        test("creates a permutation") {
            assertThat(createPermutation(1,2,3,4)).isEqualTo(Permutation(listOf(1,2,3,4)))
            assertThat(createPermutation(4,3,1,2)).isEqualTo(Permutation(listOf(4,3,1,2)))
            assertThat(createPermutation(1,4,3,2,6,5)).isEqualTo(Permutation(listOf(1,4,3,2,6,5)))
        }

        test("does not allow numbers higher than the length") {
            expectException(IllegalPermutationException::class, "invalid permutation") {
                createPermutation(1,2,3,5)
            }

            expectException(IllegalPermutationException::class, "invalid permutation") {
                createPermutation(1,2,3,4,5,9)
            }
        }

        test("does not allow non-positive numbers") {
            expectException(IllegalPermutationException::class, "invalid permutation") {
                createPermutation(1,2,3,0)
            }

            expectException(IllegalPermutationException::class, "invalid permutation") {
                createPermutation(1,2,3,4,5,-1)
            }
        }

        test("does not allow duplicates") {
            expectException(IllegalPermutationException::class, "invalid permutation") {
                createPermutation(1,2,3,3)
            }

            expectException(IllegalPermutationException::class, "invalid permutation") {
                createPermutation(1,2,3,4,5,1)
            }
        }
    }
})
