/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

/**
 * Advent of Code 2018, Day 4 - Repose Record
 *
 * Problem Description: http://adventofcode.com/2018/day/4
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2018/day4/
 */
package com.ginsberg.advent2018

class Day04(rawInput: List<String>) {

    private val sleepMinutesPerGuard: Map<Int, List<Int>> = parseInput(rawInput)

    fun solvePart1(): Int =
        sleepMinutesPerGuard
            .maxBy { it.value.size }!!
            .run { key * value.mostFrequent()!! }

    fun solvePart2(): Int =
        sleepMinutesPerGuard.flatMap { entry ->
            entry.value.map { minute ->
                entry.key to minute // Guard to Minute
            }
        }
            .mostFrequent()!! // Which guard slept the most in a minute?
            .run { first * second }

    private fun parseInput(input: List<String>): Map<Int, List<Int>> {
        val sleeps = mutableMapOf<Int, List<Int>>()
        var guard = 0
        var sleepStart = 0

        input.sorted().forEach { row ->
            when {
                row.contains("Guard") -> guard = guardPattern.single(row).toInt()
                row.contains("asleep") -> sleepStart = timePattern.single(row).toInt()
                else -> {
                    val sleepMins = (sleepStart until timePattern.single(row).toInt()).toList()
                    sleeps.merge(guard, sleepMins) { a, b -> a + b }
                }
            }
        }
        return sleeps
    }

    companion object {
        private val guardPattern = """^.+ #(\d+) .+$""".toRegex()
        private val timePattern = """^\[.+:(\d\d)] .+$""".toRegex()
    }

    private fun Regex.single(from: String): String =
        this.find(from)!!.destructured.component1()
}
