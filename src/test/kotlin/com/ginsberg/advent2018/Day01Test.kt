/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

package com.ginsberg.advent2018

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 1")
class Day01Test {

    // Given
    val sampleInput = listOf("+1", "-2", "+3", "+1")
    val actualInput = Resources.resourceAsList("day01_input.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            // When
            val day = Day01(sampleInput)

            // Then
            assertThat(day.solvePart1()).isEqualTo(3)
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day01(actualInput)

            // Then
            assertThat(day.solvePart1()).isEqualTo(599)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            // When
            val day = Day01(sampleInput)

            // Then
            assertThat(day.solvePart2()).isEqualTo(2)
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day01(actualInput)

            // Then
            assertThat(day.solvePart2()).isEqualTo(81204)
        }
    }
}