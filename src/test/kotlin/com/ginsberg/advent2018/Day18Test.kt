/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

package com.ginsberg.advent2018

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 18")
class Day18Test {

    // Given
    val sampleInput = Resources.resourceAsList("day18_input_sample.txt")
    val actualInput = Resources.resourceAsList("day18_input.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches examples`() {
            // When
            val day = Day18(sampleInput)

            // Then
            assertThat(day.solvePart1()).isEqualTo(1147)
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day18(actualInput)

            // Then
            assertThat(day.solvePart1()).isEqualTo(481290)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {


        @Test
        fun `Actual answer`() {
            // When
            val day = Day18(actualInput)

            // Then
            assertThat(day.solvePart2()).isEqualTo(180752)
        }
    }
}