/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

package com.ginsberg.advent2018

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 6")
class Day06Test {

    // Given
    val sampleInput = listOf(
        "1, 1",
        "1, 6",
        "8, 3",
        "3, 4",
        "5, 5",
        "8, 9"
    )
    val actualInput = Resources.resourceAsList("day06_input.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            // When
            val day = Day06(sampleInput)

            // Then
            assertThat(day.solvePart1()).isEqualTo(17)
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day06(actualInput)

            // Then
            assertThat(day.solvePart1()).isEqualTo(3223)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            // When
            val day = Day06(sampleInput)

            // Then
            assertThat(day.solvePart2(32)).isEqualTo(16)
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day06(actualInput)

            // Then
            assertThat(day.solvePart2()).isEqualTo(40495)
        }
    }
}