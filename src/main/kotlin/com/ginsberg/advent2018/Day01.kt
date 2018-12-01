/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

/**
 * Advent of Code 2018, Day 1 - Chronal Calibration
 *
 * Problem Description: http://adventofcode.com/2018/day/1
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2018/day1/
 */
package com.ginsberg.advent2018

class Day01(rawInput: List<String>) {

    private val input: List<Int> = rawInput.map { it.toInt() }

    fun solvePart1(): Int =
        input.sum()

    fun solvePart2(): Int {
        val frequencies = mutableSetOf(0)
        var sum = 0
        return input.toInfiniteSequence()
            .map {
                sum += it
                sum
            }
            .first { !frequencies.add(it) }
    }

}