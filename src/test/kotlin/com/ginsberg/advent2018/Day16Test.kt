/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

package com.ginsberg.advent2018

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 16")
class Day16Test {


    // Given
    val sampleInputPart1 = Resources.resourceAsList("day16_input_sample_part1.txt")
    val actualInputPart1 = Resources.resourceAsList("day16_input_part1.txt")
    val actualInputPart2 = Resources.resourceAsList("day16_input_part2.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches examples`() {
            // When
            val day = Day16(sampleInputPart1, listOf())

            // Then
            assertThat(day.solvePart1()).isEqualTo(1)
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day16(actualInputPart1, actualInputPart2)

            // Then
            assertThat(day.solvePart1()).isEqualTo(588)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {


        @Test
        fun `Actual answer`() {
            // When
            val day = Day16(actualInputPart1, actualInputPart2)

            // Then
            assertThat(day.solvePart2()).isEqualTo(627)
        }
    }
}