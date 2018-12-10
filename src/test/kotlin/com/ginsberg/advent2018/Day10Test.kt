/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

package com.ginsberg.advent2018

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 10")
class Day10Test {

    // Given
    val sampleInput = Resources.resourceAsList("day10_input_sample.txt")
    val actualInput = Resources.resourceAsList("day10_input.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            // Given
            val answer = Resources.resourceAsString("day10_part1_sample_answer.txt", "\n")

            // When
            val day = Day10(sampleInput)

            // Then
            assertThat(day.solvePart1()).isEqualTo(answer)
        }

        @Test
        fun `Actual answer`() {
            // Given
            val answer = Resources.resourceAsString("day10_part1_answer.txt", "\n")

            // When
            val day = Day10(actualInput)

            // Then
            assertThat(day.solvePart1()).isEqualTo(answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            // When
            val day = Day10(sampleInput)

            // Then
            assertThat(day.solvePart2()).isEqualTo(3)
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day10(actualInput)

            // Then
            assertThat(day.solvePart2()).isEqualTo(10813)
        }
    }
}