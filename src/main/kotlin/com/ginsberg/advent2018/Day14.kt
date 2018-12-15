/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

/**
 * Advent of Code 2018, Day 14 - Chocolate Charts
 *
 * Problem Description: http://adventofcode.com/2018/day/14
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2018/day14/
 */
package com.ginsberg.advent2018


class Day14(private val stoppingPoint: Int) {

    private val stopList = stoppingPoint.asDigits()

    fun solvePart1(): String =
        recipes { it.size == stoppingPoint + 10 }.takeLast(10).joinToString("")

    fun solvePart2(): Int =
        recipes { it.endsWith(stopList) }.size - stopList.size

    private fun recipes(stopCondition: (List<Int>) -> Boolean): List<Int> {
        val history = mutableListOf(3, 7)
        var elf1 = 0
        var elf2 = 1
        var stop = false

        while (!stop) {
            val nextValue = history[elf1] + history[elf2]
            nextValue.asDigits().forEach {
                if (!stop) {
                    history.add(it)
                    stop = stopCondition(history)
                }
            }
            elf1 = (elf1 + history[elf1] + 1) % history.size
            elf2 = (elf2 + history[elf2] + 1) % history.size
        }
        return history
    }

    private fun Int.asDigits(): List<Int> =
        this.toString().map { it.toString().toInt() }

    private fun List<Int>.endsWith(other: List<Int>): Boolean =
        if (this.size < other.size) false
        else this.slice(this.size - other.size until this.size) == other
}
