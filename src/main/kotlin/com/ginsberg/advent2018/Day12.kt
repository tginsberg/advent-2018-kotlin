/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

/**
 * Advent of Code 2018, Day 12 - Subterranean Sustainability
 *
 * Problem Description: http://adventofcode.com/2018/day/12
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2018/day12/
 */
package com.ginsberg.advent2018

class Day12(rawInput: List<String>) {

    private val rules: Set<String> = parseRules(rawInput)
    private val initialState: String = rawInput.first().substring(15)

    fun solvePart1(): Long =
        mutatePlants().drop(19).first().second

    fun solvePart2(targetGeneration: Long = 50_000_000_000): Long {
        var previousDiff = 0L
        var previousSize = 0L
        var generationNumber = 0

        // Go through the sequence until we find one that grows the same one as its previous generation
        mutatePlants().dropWhile { thisGen ->
            val thisDiff = thisGen.second - previousSize // Our diff to last generation
            if (thisDiff != previousDiff) {
                // Still changing
                previousDiff = thisDiff
                previousSize = thisGen.second
                generationNumber += 1
                true
            } else {
                // We've found it, stop dropping.
                false
            }
        }.first() // Consume first because sequences are lazy and it won't start otherwise.

        return previousSize + (previousDiff * (targetGeneration - generationNumber))
    }

    private fun mutatePlants(state: String = initialState): Sequence<Pair<String, Long>> = sequence {
        var zeroIndex = 0
        var currentState = state
        while (true) {
            // Make sure we have something to match to the left of our first real center point.
            while (!currentState.startsWith(".....")) {
                currentState = ".$currentState"
                zeroIndex++
            }
            // Make sure we have something to match to the right of our last real center point.
            while (!currentState.endsWith(".....")) {
                currentState = "$currentState."
            }

            currentState = currentState
                .toList()
                .windowed(5, 1)
                .map { it.joinToString(separator = "") }
                .map { if (it in rules) '#' else '.' }
                .joinToString(separator = "")

            zeroIndex -= 2 // Because there are two positions to the left of the first real center and were not evaluated
            yield(Pair(currentState, currentState.sumIndexesFrom(zeroIndex)))
        }
    }

    private fun String.sumIndexesFrom(zero: Int): Long =
        this.mapIndexed { idx, c -> if (c == '#') idx.toLong() - zero else 0 }.sum()

    private fun parseRules(input: List<String>): Set<String> =
        input
            .drop(2)
            .filter { it.endsWith("#") }
            .map { it.take(5) }
            .toSet()
}
