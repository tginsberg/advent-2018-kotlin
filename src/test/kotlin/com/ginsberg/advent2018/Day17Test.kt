/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

package com.ginsberg.advent2018

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 17")
class Day17Test {

    // Given
    val sampleInput = Resources.resourceAsList("day17_input_sample.txt")
    val actualInput = Resources.resourceAsList("day17_input.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches examples`() {
            // When
            val day = Day17(sampleInput)

            // Then
            assertThat(day.solvePart1()).isEqualTo(57)
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day17(actualInput)

            // Then
            assertThat(day.solvePart1()).isEqualTo(31949)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches examples`() {
            // When
            val day = Day17(sampleInput)

            // Then
            assertThat(day.solvePart2()).isEqualTo(29)
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day17(actualInput)

            // Then
            assertThat(day.solvePart2()).isEqualTo(26384)
        }
    }
}