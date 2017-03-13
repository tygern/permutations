package com.tygern.permutationstest

import com.tygern.permutations.*
import io.damo.aspen.Test
import io.damo.aspen.expectException
import org.assertj.core.api.Assertions.assertThat


class PermutationTest : Test({
    describe("p") {
        test("creates a permutation") {
            assertThat(p(1, 2, 3, 4)).isEqualTo(Permutation(listOf(1, 2, 3, 4)))
            assertThat(p(4, 3, 1, 2)).isEqualTo(Permutation(listOf(4, 3, 1, 2)))
            assertThat(p(1, 4, 3, 2, 6, 5)).isEqualTo(Permutation(listOf(1, 4, 3, 2, 6, 5)))
        }

        test("does not allow numbers higher than the length") {
            expectException(IllegalPermutationException::class, "invalid permutation") {
                p(1, 2, 3, 5)
            }

            expectException(IllegalPermutationException::class, "invalid permutation") {
                p(1, 2, 3, 4, 5, 9)
            }
        }

        test("does not allow non-positive numbers") {
            expectException(IllegalPermutationException::class, "invalid permutation") {
                p(1, 2, 3, 0)
            }

            expectException(IllegalPermutationException::class, "invalid permutation") {
                p(1, 2, 3, 4, 5, -1)
            }
        }

        test("does not allow duplicates") {
            expectException(IllegalPermutationException::class, "invalid permutation") {
                p(1, 2, 3, 3)
            }

            expectException(IllegalPermutationException::class, "invalid permutation") {
                p(1, 2, 3, 4, 5, 1)
            }
        }
    }

    describe("order") {
        test {
            assertThat(p(1, 2, 3, 4).order()).isEqualTo(4)
            assertThat(p(1, 2, 3, 4, 6, 5).order()).isEqualTo(6)
        }
    }

    describe("goesTo") {
        test {
            assertThat(p(1, 2, 3, 4).goesTo(2)).isEqualTo(2)
            assertThat(p(1, 4, 2, 3).goesTo(2)).isEqualTo(3)
            assertThat(p(1, 4, 3, 2).goesTo(4)).isEqualTo(2)
        }
    }

    describe("compose") {
        test {
            assertThat(compose(p(2, 1, 3, 4), p(1, 3, 2, 4))).isEqualTo(p(3, 1, 2, 4))
            assertThat(compose(p(1, 3, 2, 4), p(2, 1, 3, 4))).isEqualTo(p(2, 3, 1, 4))
            assertThat(compose(p(6, 1, 3, 4, 5, 2), p(1, 3, 2, 4, 6, 5))).isEqualTo(p(5, 1, 2, 4, 6, 3))
        }

        test("wrong order") {
            expectException(IllegalOperationException::class, "invalid operation") {
                compose(p(1, 3, 2, 4), p(2, 1, 3, 4, 5))
            }

            expectException(IllegalPermutationException::class, "invalid permutation") {
                p(1, 2, 3, 4, 5, 1)
            }
        }
    }

    describe("inverse") {
        test {
            assertThat(p(2, 1, 3, 4).inverse()).isEqualTo(p(2, 1, 3, 4))
            assertThat(p(3, 1, 2, 4).inverse()).isEqualTo(p(2, 3, 1, 4))
            assertThat(p(6, 1, 3, 4, 5, 2).inverse()).isEqualTo(p(2, 6, 3, 4, 5, 1))
        }

        test("identity") {
            val element = p(6, 1, 3, 4, 5, 2)

            assertThat(compose(element, element.inverse())).isEqualTo(p(1, 2, 3, 4, 5, 6))
        }
    }
})
