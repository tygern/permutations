package com.tygern.permutationstest

import com.tygern.permutations.*
import io.damo.aspen.Test
import io.damo.aspen.expectException
import org.assertj.core.api.Assertions.assertThat

class ATest : Test({
    val A3 = A(3)
    val A4 = A(4)
    val A5 = A(5)

    describe("identity") {
        test {
            assertThat(A4.identity).isEqualTo(Permutation(1, 2, 3, 4, 5))
            assertThat(A5.identity).isEqualTo(Permutation(1, 2, 3, 4, 5, 6))
        }

        test("invalid") {
            A4.apply {
                expectException(GroupOrderException::class, "Generator index must be between 1 and 4") {
                    s(5)
                }
                expectException(GroupOrderException::class, "Generator index must be between 1 and 4") {
                    s(0)
                }
            }
        }
    }

    describe("s") {
        test {
            assertThat(A4.s(1)).isEqualTo(Permutation(2, 1, 3, 4, 5))
            assertThat(A5.s(5)).isEqualTo(Permutation(1, 2, 3, 4, 6, 5))
        }
    }

    describe("element") {
        test {
            assertThat(A4.element(2, 3, 4)).isEqualTo(Permutation(1, 5, 2, 3, 4))
            assertThat(A(7).element(1, 2, 3, 7)).isEqualTo(Permutation(4, 1, 2, 3, 5, 6, 8, 7))
        }
    }

    describe("reduce") {
        test {
            A4.apply {
                assertThat(reduce(element(2, 2, 2, 3, 4, 4)))
                    .isEqualTo(Composition(s(2), s(3)))
            }

            A3.apply {
                assertThat(reduce(Permutation(4, 3, 2, 1)))
                    .isEqualTo(Composition(s(1), s(2), s(3), s(1), s(2), s(1)))
            }
        }
    }
})
