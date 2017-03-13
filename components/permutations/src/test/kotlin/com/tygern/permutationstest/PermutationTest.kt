package com.tygern.permutationstest

import com.tygern.permutations.*
import io.damo.aspen.Test
import io.damo.aspen.expectException
import org.assertj.core.api.Assertions.assertThat


class PermutationTest : Test({
    describe("constructor") {
        test("does not allow numbers higher than the length") {
            expectException(IllegalPermutationException::class, "invalid permutation") {
                Permutation(1, 2, 3, 5)
            }

            expectException(IllegalPermutationException::class, "invalid permutation") {
                Permutation(1, 2, 3, 4, 5, 9)
            }
        }

        test("does not allow non-positive numbers") {
            expectException(IllegalPermutationException::class, "invalid permutation") {
                Permutation(1, 2, 3, 0)
            }

            expectException(IllegalPermutationException::class, "invalid permutation") {
                Permutation(1, 2, 3, 4, 5, -1)
            }
        }

        test("does not allow duplicates") {
            expectException(IllegalPermutationException::class, "invalid permutation") {
                Permutation(1, 2, 3, 3)
            }

            expectException(IllegalPermutationException::class, "invalid permutation") {
                Permutation(1, 2, 3, 4, 5, 1)
            }
        }
    }

    describe("order") {
        test {
            assertThat(Permutation(1, 2, 3, 4).order).isEqualTo(4)
            assertThat(Permutation(1, 2, 3, 4, 6, 5).order).isEqualTo(6)
        }
    }

    describe("goesTo") {
        test {
            assertThat(Permutation(1, 2, 3, 4).goesTo(2)).isEqualTo(2)
            assertThat(Permutation(1, 4, 2, 3).goesTo(2)).isEqualTo(3)
            assertThat(Permutation(1, 4, 3, 2).goesTo(4)).isEqualTo(2)
        }
    }

    describe("mapsFrom") {
        test {
            assertThat(Permutation(1, 2, 3, 4).mapsFrom(2)).isEqualTo(2)
            assertThat(Permutation(1, 4, 2, 3).mapsFrom(2)).isEqualTo(4)
            assertThat(Permutation(1, 4, 3, 2).mapsFrom(3)).isEqualTo(3)
        }
    }

    describe("inverse") {
        test {
            assertThat(Permutation(2, 1, 3, 4).inverse()).isEqualTo(Permutation(2, 1, 3, 4))
            assertThat(Permutation(3, 1, 2, 4).inverse()).isEqualTo(Permutation(2, 3, 1, 4))
            assertThat(Permutation(6, 1, 3, 4, 5, 2).inverse()).isEqualTo(Permutation(2, 6, 3, 4, 5, 1))
        }
    }
})
