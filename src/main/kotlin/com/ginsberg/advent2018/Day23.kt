/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

/**
 * Advent of Code 2018, Day 23 - Experimental Emergency Teleportation
 *
 * Problem Description: http://adventofcode.com/2018/day/23
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2018/day23/
 */
package com.ginsberg.advent2018

import kotlin.math.abs


class Day23(rawInput: List<String>) {

    private val swarm: List<Nanobot> = rawInput.map { Nanobot.of(it) }


    fun solvePart1(): Int {
        val fattestBot = swarm.maxBy { it.radius }!!
        return swarm.count { fattestBot.inRange(it) }
    }

    fun solvePart2(): Int {
        val neighbors: Map<Nanobot, Set<Nanobot>> = swarm.map { bot ->
            Pair(bot, swarm.filterNot { it == bot }.filter { bot.withinRangeOfSharedPoint(it) }.toSet())
        }.toMap()

        val clique: Set<Nanobot> = BronKerbosch(neighbors).largestClique()
        return clique.map { it.location.distanceTo(Point3d.origin) - it.radius }.max()!!
    }

    private data class Nanobot(val location: Point3d, val radius: Int) {

        fun inRange(other: Nanobot): Boolean =
            location.distanceTo(other.location) <= radius

        fun withinRangeOfSharedPoint(other: Nanobot): Boolean =
            location.distanceTo(other.location) <= radius + other.radius

        companion object {

            private val digitsRegex = """^pos=<(-?\d+),(-?\d+),(-?\d+)>, r=(\d+)$""".toRegex()

            fun of(input: String): Nanobot =
                digitsRegex.find(input)?.let {
                    val (x, y, z, r) = it.destructured
                    Nanobot(Point3d(x.toInt(), y.toInt(), z.toInt()), r.toInt())
                } ?: throw IllegalArgumentException("Cannot parse $input")

        }
    }

    private data class Point3d(val x: Int, val y: Int, val z: Int) {
        fun distanceTo(other: Point3d): Int =
            abs(x - other.x) + abs(y - other.y) + abs(z - other.z)

        companion object {
            val origin = Point3d(0, 0, 0)
        }
    }

}

