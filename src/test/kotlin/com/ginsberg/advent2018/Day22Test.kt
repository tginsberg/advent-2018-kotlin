/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

package com.ginsberg.advent2018

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 22")
class Day22Test {

    // Given
    val sampleInput = listOf(
        "depth: 510",
        "target: 10,10"
    )
    val actualInput = Resources.resourceAsList("day22_input.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches examples`() {

            // When
            val day = Day22(sampleInput)

            // Then
            assertThat(day.solvePart1()).isEqualTo(114)

        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day22(actualInput)

            // Then
            assertThat(day.solvePart1()).isEqualTo(11359)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches examples`() {

            // When
            val day = Day22(sampleInput)

            // Then
            assertThat(day.solvePart2()).isEqualTo(45)

        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day22(actualInput)

            // Then
            assertThat(day.solvePart2()).isEqualTo(976)
        }
    }
}