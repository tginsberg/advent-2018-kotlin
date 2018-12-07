/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

package com.ginsberg.advent2018

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 7")
class Day07Test {

    // Given
    val sampleInput = listOf(
        "Step C must be finished before step A can begin.",
        "Step C must be finished before step F can begin.",
        "Step A must be finished before step B can begin.",
        "Step A must be finished before step D can begin.",
        "Step B must be finished before step E can begin.",
        "Step D must be finished before step E can begin.",
        "Step F must be finished before step E can begin."
    )
    val actualInput = Resources.resourceAsList("day07_input.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            // When
            val day = Day07(sampleInput)

            // Then
            assertThat(day.solvePart1()).isEqualTo("CABDFE")
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day07(actualInput)

            // Then
            assertThat(day.solvePart1()).isEqualTo("MNQKRSFWGXPZJCOTVYEBLAHIUD")
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            // When
            val day = Day07(sampleInput)

            // Then
            assertThat(day.solvePart2(2, Day07.Companion::exampleCostFunction)).isEqualTo(15)
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day07(actualInput)

            // Then
            assertThat(day.solvePart2(5, Day07.Companion::actualCostFunction)).isEqualTo(948)
        }
    }
}