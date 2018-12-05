/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

package com.ginsberg.advent2018

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 5")
class Day05Test {

    // Given
    val sampleInput = "dabAcCaCBAcCcaDA"
    val actualInput = Resources.resourceAsString("day05_input.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            // When
            val day = Day05(sampleInput)

            // Then
            assertThat(day.solvePart1()).isEqualTo(10)
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day05(actualInput)

            // Then
            assertThat(day.solvePart1()).isEqualTo(9808)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            // When
            val day = Day05(sampleInput)

            // Then
            assertThat(day.solvePart2()).isEqualTo(4)
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day05(actualInput)

            // Then
            assertThat(day.solvePart2()).isEqualTo(6484)
        }
    }
}