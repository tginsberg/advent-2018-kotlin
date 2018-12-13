/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

package com.ginsberg.advent2018

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 12")
class Day12Test {

    // Given
    val sampleInput = Resources.resourceAsList("day12_input_sample.txt")
    val actualInput = Resources.resourceAsList("day12_input.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            // When
            val day = Day12(sampleInput)

            // Then
            assertThat(day.solvePart1()).isEqualTo(325L)
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day12(actualInput)

            // Then
            assertThat(day.solvePart1()).isEqualTo(3472L)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Actual answer`() {
            // When
            val day = Day12(actualInput)

            // Then
            assertThat(day.solvePart2()).isEqualTo(2600000000919L)
        }
    }
}