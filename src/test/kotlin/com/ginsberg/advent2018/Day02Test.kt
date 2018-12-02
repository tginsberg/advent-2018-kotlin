/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

package com.ginsberg.advent2018

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 2")
class Day02Test {

    // Given
    val sampleInputPart1 = listOf("abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab")
    val sampleInputPart2 = listOf("abcde", "fghij", "klmno", "pqrst", "fguij", "axcye", "wvxyz")
    val actualInput = Resources.resourceAsList("day02_input.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            // When
            val day = Day02(sampleInputPart1)

            // Then
            assertThat(day.solvePart1()).isEqualTo(12)
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day02(actualInput)

            // Then
            assertThat(day.solvePart1()).isEqualTo(8892)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            // When
            val day = Day02(sampleInputPart2)

            // Then
            assertThat(day.solvePart2()).isEqualTo("fgij")
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day02(actualInput)

            // Then
            assertThat(day.solvePart2()).isEqualTo("zihwtxagifpbsnwleydukjmqv")
        }
    }
}