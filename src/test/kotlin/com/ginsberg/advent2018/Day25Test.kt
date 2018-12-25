/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

package com.ginsberg.advent2018

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 25")
class Day25Test {

    // Given
    val sampleInput = listOf(
        Pair("day25_input_sample_1.txt", 2),
        Pair("day25_input_sample_2.txt", 4),
        Pair("day25_input_sample_3.txt", 3),
        Pair("day25_input_sample_4.txt", 8)
    )
    val actualInput = Resources.resourceAsList("day25_input.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches examples`() {
            // Given
            sampleInput.forEach { (file, answer) ->

                // When
                val day = Day25(Resources.resourceAsList(file))

                // Then
                assertThat(day.solvePart1()).isEqualTo(answer)
            }

        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day25(actualInput)

            // Then
            assertThat(day.solvePart1()).isEqualTo(388)
        }
    }
}