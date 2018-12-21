/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

package com.ginsberg.advent2018

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 20")
class Day20Test {

    // Given
    val sampleInput = listOf(
        """^WNE$""" to 3,
        """^ENWWW(NEEE|SSE(EE|N))$""" to 10,
        """^ENNWSWW(NEWS|)SSSEEN(WNSE|)EE(SWEN|)NNN$""" to 18
    )
    val actualInput = Resources.resourceAsString("day20_input.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches examples`() {
            // Given
            sampleInput.forEach { input ->

                // When
                val day = Day20(input.first)

                // Then
                assertThat(day.solvePart1()).isEqualTo(input.second)
            }
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day20(actualInput)

            // Then
            assertThat(day.solvePart1()).isEqualTo(3699)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {


        @Test
        fun `Actual answer`() {
            // When
            val day = Day20(actualInput)

            // Then
            assertThat(day.solvePart2()).isEqualTo(8517)
        }
    }
}