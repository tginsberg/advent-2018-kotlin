/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

/**
 * Advent of Code 2018, Day 17 - Reservoir Research
 *
 * Problem Description: http://adventofcode.com/2018/day/17
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2018/day17/
 */
package com.ginsberg.advent2018

class Day17(rawInput: List<String>) {

    private val parsedData = createMap(rawInput)
    private val grid: Array<CharArray> = parsedData.first
    private val minY: Int = parsedData.second
    private val fountain: Point = Point(500, 0)

    fun solvePart1(): Int {
        flow(fountain)
        return grid.filterIndexed { idx, _ -> idx >= minY }.sumBy { row ->
            row.filter { it in flowOrStill }.count()
        }
    }

    fun solvePart2(): Int {
        flow(fountain)
        return grid.filterIndexed { idx, _ -> idx >= minY }.sumBy { row ->
            row.filter { it == '~' }.count()
        }
    }

    private fun flow(source: Point) {
        if (source.down !in grid) {
            return
        }
        if (grid[source.down] == '.') {
            grid[source.down] = '|'
            flow(source.down)
        }
        if (grid[source.down] in wallOrStill && source.right in grid && grid[source.right] == '.') {
            grid[source.right] = '|'
            flow(source.right)
        }
        if (grid[source.down] in wallOrStill && source.left in grid && grid[source.left] == '.') {
            grid[source.left] = '|'
            flow(source.left)
        }
        if (hasWalls(source)) {
            fillLeftAndRight(source)
        }
    }

    private fun hasWalls(source: Point): Boolean =
        hasWall(source, Point::right) && hasWall(source, Point::left)

    private fun hasWall(source: Point, nextPoint: (Point) -> Point): Boolean {
        var point = source
        while (point in grid) {
            when (grid[point]) {
                '#' -> return true
                '.' -> return false
                else -> point = nextPoint(point)
            }
        }
        return false
    }

    private fun fillLeftAndRight(source: Point) {
        fillUntilWall(source, Point::right)
        fillUntilWall(source, Point::left)
    }

    private fun fillUntilWall(source: Point, nextPoint: (Point) -> Point) {
        var point = source
        while (grid[point] != '#') {
            grid[point] = '~'
            point = nextPoint(point)
        }
    }


    private fun createMap(input: List<String>): Pair<Array<CharArray>, Int> {
        val spots = claySpotsFromInput(input)
        val minY = spots.minBy { it.y }!!.y
        val maxX = spots.maxBy { it.x }!!.x
        val maxY = spots.maxBy { it.y }!!.y

        // Generate based off of maximum sizes
        val grid: Array<CharArray> = (0..maxY).map {
            // Account for zero indexing and flowing off the right side of the map!
            CharArray(maxX + 2).apply { fill('.') }
        }.toTypedArray()

        // Add all clay spots to the grid
        spots.forEach { spot ->
            grid[spot] = '#'
        }
        // Add the fountain
        grid[0][500] = '+'

        return Pair(grid, minY)
    }

    private fun claySpotsFromInput(input: List<String>): List<Point> =
        input.flatMap { row ->
            val digits = row.toIntArray()
            if (row.startsWith("y")) {
                (digits[1]..digits[2]).map { Point(it, digits[0]) }
            } else {
                (digits[1]..digits[2]).map { Point(digits[0], it) }
            }
        }

    companion object {
        private val nonDigits = """[xy=,]""".toRegex()
        private val wallOrStill = setOf('#', '~')
        private val flowOrStill = setOf('|', '~')

        private fun String.toIntArray(): IntArray =
            this.replace(nonDigits, "").replace("..", " ").split(" ").map { it.toInt() }.toIntArray()
    }
}
