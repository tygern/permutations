package com.tygern.permutationstest

import com.tygern.permutations.*
import io.damo.aspen.Test
import io.damo.aspen.expectException
import org.assertj.core.api.Assertions.assertThat

class TypeATest : Test({

    describe("identity") {
        test {
            assertThat(A(4).identity).isEqualTo(Permutation(1, 2, 3, 4, 5))
            assertThat(A(5).identity).isEqualTo(Permutation(1, 2, 3, 4, 5, 6))
        }

        test("invalid") {
            expectException(GroupOrderException::class, "Generator index must be between 1 and 4") {
                A(4).s(5)
            }
            expectException(GroupOrderException::class, "Generator index must be between 1 and 4") {
                A(4).s(0)
            }
        }
    }

    describe("s") {
        test {
            assertThat(A(4).s(1)).isEqualTo(Permutation(2, 1, 3, 4, 5))
            assertThat(A(5).s(5)).isEqualTo(Permutation(1, 2, 3, 4, 6, 5))
        }
    }

    describe("element") {
        test {
            assertThat(A(4).element(2, 3, 4)).isEqualTo(Permutation(1, 5, 2, 3, 4))
            assertThat(A(7).element(1, 2, 3, 7)).isEqualTo(Permutation(4, 1, 2, 3, 5, 6, 8, 7))
        }
    }
})
