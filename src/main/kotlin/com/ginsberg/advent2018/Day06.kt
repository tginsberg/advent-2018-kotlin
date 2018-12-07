/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

/**
 * Advent of Code 2018, Day 6 - Chronal Coordinates
 *
 * Problem Description: http://adventofcode.com/2018/day/6
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2018/day6/
 */
package com.ginsberg.advent2018

class Day06(input: List<String>) {

    private val points: List<Point> = input.map { Point.of(it) }
    private val xRange: IntRange = (points.minBy { it.x }!!.x..points.maxBy { it.x }!!.x)
    private val yRange: IntRange = (points.minBy { it.y }!!.y..points.maxBy { it.y }!!.y)

    fun solvePart1(): Int {
        val infinite: MutableSet<Point> = mutableSetOf()
        return xRange.asSequence().flatMap { x ->
            yRange.asSequence().map { y ->
                val closest = points.map { it to it.distanceTo(x, y) }.sortedBy { it.second }.take(2)
                if (isEdge(x, y)) {
                    infinite.add(closest[0].first)
                }
                closest[0].first.takeUnless { closest[0].second == closest[1].second }
            }
        }
            .filterNot { it in infinite }
            .groupingBy { it }
            .eachCount()
            .maxBy { it.value }!!
            .value
    }

    fun solvePart2(range: Int = 10_000): Int =
        xRange.asSequence().flatMap { x ->
            yRange.asSequence().map { y ->
                points.map { it.distanceTo(x, y) }.sum()
            }
        }
            .filter { it < range }
            .count()

    private fun isEdge(x: Int, y: Int): Boolean =
        x == xRange.first || x == xRange.last || y == yRange.first || y == yRange.last

}
