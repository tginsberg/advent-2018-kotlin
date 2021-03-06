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

    @DisplayName("Most Frequent Element")
    @Nested
    inner class MostFrequentElement {

        @Test
        @DisplayName("Empty means null")
        fun emptyList() {
            // Given
            val input = emptyList<String>()

            // Then
            assertThat(input.mostFrequent()).isNull()
        }

        @Test
        @DisplayName("Finds most frequent")
        fun findsMostFrequent() {
            // Given
            val input = listOf(1, 1, 2, 2, 2)

            // Then
            assertThat(input.mostFrequent()).isEqualTo(2)
        }
    }

    @DisplayName("IntRange Span")
    @Nested
    inner class IntRangeSpan {

        @Test
        @DisplayName("Zero range is zero")
        fun zeroMeansZero() {
            // Given
            val range = IntRange(0, 0)

            // Then
            assertThat(range.span).isEqualTo(0L)
        }

        @Test
        @DisplayName("Both Positive")
        fun bothPositive() {
            // Given
            val range = IntRange(10, 20)

            // Then
            assertThat(range.span).isEqualTo(10)
        }

        @Test
        @DisplayName("Both Negative")
        fun bothNegative() {
            // Given
            val range = IntRange(-20, -10)

            // Then
            assertThat(range.span).isEqualTo(10)
        }

        @Test
        @DisplayName("Negative through Positive")
        fun negativeThroughPositive() {
            // Given
            val range = IntRange(-20, 20)

            // Then
            assertThat(range.span).isEqualTo(40)
        }
    }

}