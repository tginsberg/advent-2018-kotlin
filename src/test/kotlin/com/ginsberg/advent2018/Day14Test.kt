/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

package com.ginsberg.advent2018

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 14")
class Day14Test {

    // Given
    val actualInput = Resources.resourceAsString("day14_input.txt").toInt()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches examples`() {
            // When
            val day = Day14(9)

            // Then
            assertThat(day.solvePart1()).isEqualTo("5158916779")
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day14(actualInput)

            // Then
            assertThat(day.solvePart1()).isEqualTo("2103141159")
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        private val sampleInputs = listOf(
            Pair(51589, 9),
            Pair(1245, 6),
            Pair(92510, 18),
            Pair(59414, 2018)
        )

        @Test
        fun `Matches examples`() {
            sampleInputs.forEach { input ->
                // When
                val day = Day14(input.first)

                // Then
                assertThat(day.solvePart2()).isEqualTo(input.second)
            }

        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day14(actualInput)

            // Then
            assertThat(day.solvePart2()).isEqualTo(20_165_733)
        }
    }
}