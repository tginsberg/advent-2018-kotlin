/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

package com.ginsberg.advent2018

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 9")
class Day09Test {

    // Given
    val sampleInput = listOf(
        Triple(9, 25, 32L),
        Triple(10, 1618, 8317L),
        Triple(13, 7999, 146373L),
        Triple(17, 1104, 2764L),
        Triple(21, 6111, 54718L),
        Triple(30, 5807, 37305L)
    )
    val actualInput = Resources.resourceAsString("day09_input.txt")
        .split(" ")
        .run { this[0].toInt() to this[6].toInt() }

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            sampleInput.forEach { input ->
                // When
                val day = Day09(input.first, input.second)

                // Then
                assertThat(day.solvePart1()).isEqualTo(input.third)
            }

        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day09(actualInput.first, actualInput.second)

            // Then
            assertThat(day.solvePart1()).isEqualTo(404_611)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Actual answer`() {
            // When
            val day = Day09(actualInput.first, actualInput.second)

            // Then
            assertThat(day.solvePart2()).isEqualTo(3_350_093_681)
        }
    }
}