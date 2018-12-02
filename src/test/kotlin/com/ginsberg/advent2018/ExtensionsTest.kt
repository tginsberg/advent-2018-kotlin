/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

package com.ginsberg.advent2018

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Extension Tests")
internal class ExtensionsTest {

    @DisplayName("Infinite Sequence from List")
    @Nested
    inner class ListToSequence {

        @Test
        @DisplayName("Sequence loops around on list")
        fun loopAround() {
            // Given
            val input = listOf(1, 2, 3)

            // Then
            assertThat(input.toInfiniteSequence().take(7).toList())
                .isEqualTo(listOf(1, 2, 3, 1, 2, 3, 1))
        }

        @Test
        @DisplayName("Detects empty list")
        fun emptyList() {
            // Given
            val input = emptyList<Int>()

            // Then
            assertThat(input.toInfiniteSequence().toList())
                .isEmpty()
        }
    }

}