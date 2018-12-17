/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

package com.ginsberg.advent2018

import kotlin.math.abs

data class Point(val x: Int, val y: Int) : Comparable<Point> {

    fun distanceTo(otherX: Int, otherY: Int): Int =
        abs(x - otherX) + abs(y - otherY)

    fun distanceTo(other: Point): Int =
        distanceTo(other.x, other.y)

    fun cardinalNeighbors(allowNegative: Boolean = true): List<Point> =
        // Note: Generate in reading order!
        listOf(
            Point(x, y - 1),
            Point(x - 1, y),
            Point(x + 1, y),
            Point(x, y + 1)
        ).filter { allowNegative || it.x >= 0 && it.y >= 0 }

    override fun compareTo(other: Point): Int =
        when {
            y < other.y -> -1
            y > other.y -> 1
            x < other.x -> -1
            x > other.x -> 1
            else -> 0
        }

    companion object {
        fun of(input: String): Point =
            input.split(",")
                .map { it.trim().toInt() }
                .run { Point(this[0], this[1]) }
    }
}