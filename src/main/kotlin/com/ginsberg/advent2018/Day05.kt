/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

/**
 * Advent of Code 2018, Day 5 - Alchemical Reduction
 *
 * Problem Description: http://adventofcode.com/2018/day/5
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2018/day5/
 */
package com.ginsberg.advent2018

class Day05(private val input: String) {

    fun solvePart1(): Int =
        input.react().length

    fun solvePart2(): Int =
        ('A'..'Z')
            .map { input.react(it).length }
            .min()
            ?: throw IllegalStateException()

    private fun String.react(ignoring: Char? = null): String =
        this.fold(mutableListOf<Char>()) { done, char ->
            when {
                ignoring != null && char.equals(ignoring, true) -> Unit
                done.firstOrNull() matches char -> done.removeAt(0)
                else -> done.add(0, char)
            }
            done
        }
            .reversed()
            .joinToString(separator = "")

    private infix fun Char?.matches(other: Char): Boolean =
        when {
            this == null -> false
            this.toUpperCase() != other.toUpperCase() -> false
            this.isUpperCase() && other.isLowerCase() -> true
            this.isLowerCase() && other.isUpperCase() -> true
            else -> false
        }

}