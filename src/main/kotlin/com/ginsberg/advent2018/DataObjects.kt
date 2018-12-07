/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

package com.ginsberg.advent2018

import kotlin.math.abs

data class Point(val x: Int, val y: Int) {
    fun distanceTo(otherX: Int, otherY: Int): Int =
        abs(x - otherX) + abs(y - otherY)

    companion object {
        fun of(input: String): Point =
            input.split(",")
                .map { it.trim().toInt() }
                .run { Point(this[0], this[1]) }
    }
}