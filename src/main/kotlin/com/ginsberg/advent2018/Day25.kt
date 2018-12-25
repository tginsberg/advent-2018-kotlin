/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

/**
 * Advent of Code 2018, Day 25 - Four-Dimensional Adventure
 *
 * Problem Description: http://adventofcode.com/2018/day/25
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2018/day25/
 */
package com.ginsberg.advent2018

import java.lang.Math.abs
import java.util.ArrayDeque


class Day25(rawInput: List<String>) {

    private val points: List<Point4d> = rawInput.map { Point4d.of(it) }

    fun solvePart1(): Int =
        constellations(mapNeighbors()).count()

    private fun mapNeighbors(): Map<Point4d, Set<Point4d>> =
        points.map { point ->
            Pair(point, points.filterNot { it == point }.filter { point.distanceTo(it) <= 3 }.toSet())
        }.toMap()

    private fun constellations(neighbors: Map<Point4d, Set<Point4d>>): Sequence<Set<Point4d>> = sequence {
        val allPoints = neighbors.keys.toMutableList()
        while (allPoints.isNotEmpty()) {
            val point = allPoints.removeAt(0)
            val thisConstellation = mutableSetOf(point)
            val foundNeighbors = ArrayDeque<Point4d>(neighbors.getValue(point))
            while (foundNeighbors.isNotEmpty()) {
                foundNeighbors.removeFirst().also {
                    allPoints.remove(it) // Not the basis of a new constellation
                    thisConstellation.add(it)   // Because it is part of our constellation

                    // And all this point's neighbors are therefore part of our constellation too
                    foundNeighbors.addAll(neighbors.getValue(it).filterNot { other -> other in thisConstellation })
                }
            }
            // We've run out of found neighbors to check, this is a constellation.
            yield(thisConstellation)
        }
    }

    private data class Point4d(val x: Int, val y: Int, val z: Int, val t: Int) {

        fun distanceTo(other: Point4d): Int =
            abs(x - other.x) + abs(y - other.y) + abs(z - other.z) + abs(t - other.t)

        companion object {
            fun of(input: String): Point4d {
                val (x, y, z, t) = input.split(",").map { it.trim().toInt() }
                return Point4d(x, y, z, t)
            }
        }
    }
}