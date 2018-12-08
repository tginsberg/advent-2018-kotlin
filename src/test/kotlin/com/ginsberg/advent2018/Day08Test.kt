/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

package com.ginsberg.advent2018

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 8")
class Day08Test {

    // Given
    val sampleInput = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2"
    val actualInput = Resources.resourceAsString("day08_input.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            // When
            val day = Day08(sampleInput)

            // Then
            assertThat(day.solvePart1()).isEqualTo(138)
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day08(actualInput)

            // Then
            assertThat(day.solvePart1()).isEqualTo(36891)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            // When
            val day = Day08(sampleInput)

            // Then
            assertThat(day.solvePart2()).isEqualTo(66)
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day08(actualInput)

            // Then
            assertThat(day.solvePart2()).isEqualTo(20083)
        }
    }
}