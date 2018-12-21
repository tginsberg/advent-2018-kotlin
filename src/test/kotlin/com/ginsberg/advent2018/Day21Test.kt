/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

package com.ginsberg.advent2018

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 21")
class Day21Test {

    // Given
    val actualInput = Resources.resourceAsList("day21_input.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Actual answer`() {
            // When
            val day = Day21(actualInput)

            // Then
            assertThat(day.solvePart1()).isEqualTo(103548)
        }
    }

    @Nested
    @DisplayName("Part 2")
    @Disabled("This is suuuuuuper slow (~1m37s)")
    inner class Part2 {


        @Test
        fun `Actual answer`() {
            // When
            val day = Day21(actualInput)

            // Then
            assertThat(day.solvePart2()).isEqualTo(14256686)
        }
    }
}