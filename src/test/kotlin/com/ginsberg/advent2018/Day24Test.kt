/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

package com.ginsberg.advent2018

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 24")
class Day24Test {

    // Given
    val sampleInputImmune = listOf(
        "17 units each with 5390 hit points (weak to radiation, bludgeoning) with an attack that does 4507 fire damage at initiative 2",
        "989 units each with 1274 hit points (immune to fire; weak to bludgeoning, slashing) with an attack that does 25 slashing damage at initiative 3"
    )
    val sampleInputInfection = listOf(
        "801 units each with 4706 hit points (weak to radiation) with an attack that does 116 bludgeoning damage at initiative 1",
        "4485 units each with 2961 hit points (immune to radiation; weak to fire, cold) with an attack that does 12 slashing damage at initiative 4"
    )
    val actualInputImmuneSystem = Resources.resourceAsList("day24_input_immune.txt")
    val actualInputInfection = Resources.resourceAsList("day24_input_infection.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches examples`() {

            // When
            val day = Day24(sampleInputImmune, sampleInputInfection)

            // Then
            assertThat(day.solvePart1()).isEqualTo(5216)

        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day24(actualInputImmuneSystem, actualInputInfection)

            // Then
            assertThat(day.solvePart1()).isEqualTo(21127)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches examples`() {

            // When
            val day = Day24(sampleInputImmune, sampleInputInfection)

            // Then
            assertThat(day.solvePart2()).isEqualTo(51)

        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day24(actualInputImmuneSystem, actualInputInfection)

            // Then
            assertThat(day.solvePart2()).isEqualTo(2456)
        }
    }
}