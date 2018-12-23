/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

package com.ginsberg.advent2018

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 23")
class Day23Test {

    // Given
    val actualInput = Resources.resourceAsList("day23_input.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        val sampleInput = Resources.resourceAsList("day23_input_sample_part1.txt")

        @Test
        fun `Matches examples`() {

            // When
            val day = Day23(sampleInput)

            // Then
            assertThat(day.solvePart1()).isEqualTo(7)

        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day23(actualInput)

            // Then
            assertThat(day.solvePart1()).isEqualTo(326)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        val sampleInput = Resources.resourceAsList("day23_input_sample_part2.txt")

        @Test
        fun `Matches examples`() {

            // When
            val day = Day23(sampleInput)

            // Then
            assertThat(day.solvePart2()).isEqualTo(36)

        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day23(actualInput)

            // Then
            assertThat(day.solvePart2()).isEqualTo(142473501)
        }
    }
}