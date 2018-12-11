/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

package com.ginsberg.advent2018

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 11")
class Day11Test {

    // Given
    val sampleInput = listOf(
        intArrayOf(18, 33, 45, 90, 269, 16),
        intArrayOf(42, 21, 61, 232, 251, 12)

    )
    val actualInput = Resources.resourceAsString("day11_input.txt").toInt()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches examples`() {
            // Given
            sampleInput.forEach { input ->
                // When
                val day = Day11(input.first())

                // Then
                assertThat(day.solvePart1()).isEqualTo(Pair(input[1], input[2]))
            }

        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day11(actualInput)

            // Then
            assertThat(day.solvePart1()).isEqualTo(Pair(21, 37))
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            // Given
            sampleInput.forEach { input ->
                // When
                val day = Day11(input.first())

                // Then
                assertThat(day.solvePart2()).isEqualTo(Triple(input[3], input[4], input[5]))
            }

        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day11(actualInput)

            // Then
            assertThat(day.solvePart2()).isEqualTo(Triple(236, 146, 12))
        }
    }
}