/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

package com.ginsberg.advent2018

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 15")
class Day15Test {

    // Given
    val actualInput = Resources.resourceAsList("day15_input.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        // Given
        val sampleInput = listOf(
           Pair(Resources.resourceAsList("day15_input_sample1.txt"), 27730),
            Pair(Resources.resourceAsList("day15_input_sample2.txt"), 36334),
            Pair(Resources.resourceAsList("day15_input_sample3.txt"), 39514),
            Pair(Resources.resourceAsList("day15_input_sample4.txt"), 27755),
            Pair(Resources.resourceAsList("day15_input_sample5.txt"), 28944),
            Pair(Resources.resourceAsList("day15_input_sample6.txt"), 18740),
            Pair(Resources.resourceAsList("day15_input_sample7.txt"), 13400),
            Pair(Resources.resourceAsList("day15_input_sample8.txt"), 13987)
        )

        @Test
        fun `Matches examples`() {
            sampleInput.forEach { input ->
                // When
                val day = Day15(input.first)

                // Then
                assertThat(day.solvePart1()).isEqualTo(input.second)
            }
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day15(actualInput)

            // Then
            assertThat(day.solvePart1()).isEqualTo(245280)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        // Given
        val sampleInput = listOf(
            Pair(Resources.resourceAsList("day15_input_sample1.txt"), 4988),
            Pair(Resources.resourceAsList("day15_input_sample3.txt"), 31284),
            Pair(Resources.resourceAsList("day15_input_sample4.txt"), 3478),
            Pair(Resources.resourceAsList("day15_input_sample5.txt"), 6474),
            Pair(Resources.resourceAsList("day15_input_sample6.txt"), 1140)
        )

        @Test
        fun `Matches examples`() {
            sampleInput.forEach { input ->
                // When
                val day = Day15(input.first)

                // Then
                assertThat(day.solvePart2()).isEqualTo(input.second)
            }
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day15(actualInput)

            // Then
            assertThat(day.solvePart2()).isEqualTo(74984)
        }
    }
}