/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

/**
 * Advent of Code 2018, Day 20 - A Regular Map
 *
 * Problem Description: http://adventofcode.com/2018/day/20
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2018/day20/
 */
package com.ginsberg.advent2018

import java.util.ArrayDeque


class Day20(rawInput: String) {

    private val grid: Map<Point, Int> = parseGrid(rawInput)

    fun solvePart1(): Int =
        grid.maxBy { it.value }!!.value

    fun solvePart2(): Int =
        grid.count { it.value >= 1000 }


    private fun parseGrid(input: String): Map<Point, Int> {
        val grid = mutableMapOf(startingPoint to 0)
        val stack = ArrayDeque<Point>()
        var current = startingPoint
        input.forEach {
            when (it) {
                '(' -> stack.push(current)
                ')' -> current = stack.pop()
                '|' -> current = stack.peek()
                in movementRules -> {
                    // If we are moving to a spot we haven't seen before, we can
                    // record this as a new distance.
                    val nextDistance = grid.getValue(current) + 1
                    current = movementRules.getValue(it).invoke(current)
                    grid[current] = minOf(grid.getOrDefault(current, Int.MAX_VALUE), nextDistance)
                }
            }
        }
        return grid
    }

    companion object {
        private val startingPoint = Point(0, 0)
        private val movementRules = mapOf(
            'N' to Point::up,
            'S' to Point::down,
            'E' to Point::right,
            'W' to Point::left
        )
    }
}
