/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

/**
 * Advent of Code 2018, Day 10 - The Stars Align
 *
 * Problem Description: http://adventofcode.com/2018/day/10
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2018/day10/
 */
package com.ginsberg.advent2018

class Day10(rawInput: List<String>) {

    private val message: Message = Message(rawInput.map { Light.of(it) })

    fun solvePart1(): String =
        message.resolveMessage().first

    fun solvePart2(): Int =
        message.resolveMessage().second

    private class Message(val lights: List<Light>) {

        fun resolveMessage(): Pair<String, Int> {
            var lastArea = Long.MAX_VALUE
            var thisArea = skyArea()
            var timeToResolve = -1 // Account for extra step at the end
            while (thisArea < lastArea) {
                moveLights()
                lastArea = thisArea
                thisArea = skyArea()
                timeToResolve++
            }
            moveLights(false) // We've moved one too far, back everything up one.

            return Pair(this.toString(), timeToResolve)
        }

        private fun moveLights(forward: Boolean = true) =
            lights.forEach { it.move(forward) }

        private fun skyArea(): Long =
            rangeX().span * rangeY().span

        private fun rangeX(): IntRange =
            IntRange(lights.minBy { it.x }!!.x, lights.maxBy { it.x }!!.x)

        private fun rangeY(): IntRange =
            IntRange(lights.minBy { it.y }!!.y, lights.maxBy { it.y }!!.y)

        override fun toString(): String {
            val lightSet = lights.map { Pair(it.x, it.y) }.toSet()
            return rangeY().joinToString(separator = "\n") { y ->
                rangeX().map { x ->
                    if (Pair(x, y) in lightSet) '#' else '.'
                }.joinToString(separator = "")
            }
        }
    }

    private class Light(var x: Int, var y: Int, val dX: Int, val dY: Int) {
        fun move(forward: Boolean = true) {
            if (forward) {
                x += dX
                y += dY
            } else {
                x -= dX
                y -= dY
            }
        }

        companion object {
            fun of(input: String): Light =
                input.split(",", "<", ">").map { it.trim() }.run {
                    Light(this[1].toInt(), this[2].toInt(), this[4].toInt(), this[5].toInt())
                }
        }
    }
}
