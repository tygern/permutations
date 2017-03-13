package com.tygern.permutationstest

import com.tygern.permutations.Composition
import com.tygern.permutations.IllegalCompositionException
import com.tygern.permutations.Permutation
import io.damo.aspen.Test
import io.damo.aspen.expectException
import org.assertj.core.api.Assertions.assertThat


class CompositionTestTest : Test({
    val p1 = Permutation(2, 1, 3, 4)
    val p2 = Permutation(1, 3, 2, 4)

    describe("constructor") {
        test("wrong order") {
            expectException(IllegalCompositionException::class, "invalid composition") {
                Composition(p2, Permutation(2, 1, 3, 4, 5))
            }
        }
    }

    describe("reduce") {
        test {
            assertThat(Composition(p1, p2).reduce()).isEqualTo(Permutation(3, 1, 2, 4))
            assertThat(Composition(p2, p1).reduce()).isEqualTo(Permutation(2, 3, 1, 4))

            assertThat(Composition(Permutation(6, 1, 3, 4, 5, 2), Permutation(1, 3, 2, 4, 6, 5)).reduce())
                    .isEqualTo(Permutation(5, 1, 2, 4, 6, 3))
        }

        test("more than two") {
            assertThat(Composition(Permutation(1, 2, 4, 3), p1, p2).reduce()).isEqualTo(Permutation(3, 1, 4, 2))
        }
    }
})
