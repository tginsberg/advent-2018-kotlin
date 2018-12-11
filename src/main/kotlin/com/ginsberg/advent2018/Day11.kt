/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

/**
 * Advent of Code 2018, Day 11 - Chronal Charge
 *
 * Problem Description: http://adventofcode.com/2018/day/11
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2018/day11/
 */
package com.ginsberg.advent2018

class Day11(private val serialNumber: Int, private val gridSize: Int = 300) {

    private val summedAreaTable: Array<IntArray> = createSummedAreaTable()

    fun solvePart1(): Pair<Int, Int> =
        bestSquareBetweenSizes(3, 3).run { Pair(x, y) }

    fun solvePart2(): Triple<Int, Int, Int> =
        bestSquareBetweenSizes(1, gridSize).run { Triple(x, y, n) }

    private fun bestSquareBetweenSizes(smallest: Int, largest: Int): GridSquare =
        (smallest..largest).asSequence().flatMap { n ->
            (n until gridSize).asSequence().flatMap { y ->
                (n until gridSize).asSequence().map { x ->
                    GridSquare(x - n + 1, y - n + 1, sumOfSquare(x, y, n), n)
                }
            }
        }.maxBy { it.power }!!

    private fun sumOfSquare(x: Int, y: Int, n: Int): Int {
        val lowerRight = summedAreaTable[y][x]
        val upperRight = if (y - n >= 0) summedAreaTable[y - n][x] else 0
        val lowerLeft = if (x - n >= 0) summedAreaTable[y][x - n] else 0
        val upperLeft = if (x - n >= 0 && y - n >= 0) summedAreaTable[y - n][x - n] else 0
        return lowerRight + upperLeft - upperRight - lowerLeft
    }

    private fun createSummedAreaTable(): Array<IntArray> {
        // Create an empty grid
        val summedGrid: Array<IntArray> = Array(gridSize) { IntArray(gridSize) }

        // Fill the grid
        (0 until gridSize).forEach { y ->
            (0 until gridSize).forEach { x ->
                val me = powerLevel(x, y)
                val up = if (y == 0) 0 else summedGrid[y - 1][x]
                val left = if (x == 0) 0 else summedGrid[y][x - 1]
                val upperLeft = if (x == 0 || y == 0) 0 else summedGrid[y - 1][x - 1]
                summedGrid[y][x] = me + up + left - upperLeft
            }
        }

        return summedGrid
    }

    private fun powerLevel(x: Int, y: Int): Int {
        val rackId = x + 10
        return (((rackId * y) + serialNumber) * rackId).hundreds() - 5
    }

    private fun Int.hundreds(): Int = (this / 100) % 10

    private class GridSquare(val x: Int, val y: Int, val power: Int, val n: Int)

}
