/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

/**
 * Advent of Code 2018, Day 2 - Inventory Management System
 *
 * Problem Description: http://adventofcode.com/2018/day/2
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2018/day2/
 */
package com.ginsberg.advent2018

class Day02(private val input: List<String>) {

    fun solvePart1(): Int {
        val pairs = input.map { it.findLetterSets() }
        return pairs.count { it.first } * pairs.count { it.second }
    }

    fun solvePart2(): String =
        input
            .asSequence()
            .mapIndexed { i, outer ->
                input.asSequence().drop(i).mapNotNull { inner ->
                    val diff = outer.diffIndexes(inner)
                    if (diff.size == 1) {
                        outer.removeAt(diff.first())
                    } else {
                        null
                    }
                }
            }
            .flatten()
            .first()

    // Flag whether the given String has any sets of 2 or 3 matching chars.
    // Pair.first == 2
    // Pair.second == 3
    private fun String.findLetterSets(): Pair<Boolean, Boolean> {
        val byChar = this.groupingBy { it }.eachCount()
        return Pair(
            byChar.any { it.value == 2 },
            byChar.any { it.value == 3 }
        )
    }

    // Rebuild a String with the given index missing (remove a character)
    private fun String.removeAt(index: Int): String =
        if (this.length <= 1) ""
        else this.substring(0 until index) + this.substring(index + 1)

    // Get the List of indexes where these two Strings differ.
    // Assumption untested: Strings are equal in length.
    private fun String.diffIndexes(other: String): List<Int> =
        this
            .mapIndexed { idx, char -> if (other[idx] != char) idx else null }
            .filterNotNull()

}