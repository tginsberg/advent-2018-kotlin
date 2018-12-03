/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

package com.ginsberg.advent2018

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 3")
class Day03Test {

    // Given
    val sampleInput = listOf(
        "#1 @ 1,3: 4x4",
        "#2 @ 3,1: 4x4",
        "#3 @ 5,5: 2x2"
    )
    val actualInput = Resources.resourceAsList("day03_input.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            // When
            val day = Day03(sampleInput)

            // Then
            assertThat(day.solvePart1()).isEqualTo(4)
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day03(actualInput)

            // Then
            assertThat(day.solvePart1()).isEqualTo(121163)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            // When
            val day = Day03(sampleInput)

            // Then
            assertThat(day.solvePart2()).isEqualTo(3)
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day03(actualInput)

            // Then
            assertThat(day.solvePart2()).isEqualTo(943)
        }
    }
}