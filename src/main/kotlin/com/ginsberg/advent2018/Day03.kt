/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

/**
 * Advent of Code 2018, Day 3 - No Matter How You Slice It
 *
 * Problem Description: http://adventofcode.com/2018/day/3
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2018/day3/
 */
package com.ginsberg.advent2018

class Day03(rawInput: List<String>) {

    private val claims = rawInput.map { Claim.parse(it) }

    fun solvePart1(): Int =
        claims
            .flatMap { it.area() }
            .groupingBy { it }
            .eachCount()
            .count { it.value > 1 }

    fun solvePart2(): Int {
        val cloth = mutableMapOf<Pair<Int, Int>, Int>()
        val uncovered = claims.map { it.id }.toMutableSet()
        claims.forEach { claim ->
            claim.area().forEach { spot ->
                val found = cloth.getOrPut(spot) { claim.id }
                if (found != claim.id) {
                    uncovered.remove(found)
                    uncovered.remove(claim.id)
                }
            }
        }
        return uncovered.first()
    }

}

data class Claim(val id: Int, val left: Int, val top: Int, val width: Int, val height: Int) {
    fun area(): List<Pair<Int, Int>> =
        (0 + left until width + left).flatMap { w ->
            (0 + top until height + top).map { h ->
                Pair(w, h)
            }
        }

    // This code parses a String into a Claim, using a Regular Expression
    companion object {
        private val pattern = """^#(\d+) @ (\d+),(\d+): (\d+)x(\d+)$""".toRegex()
        fun parse(input: String): Claim {
            return pattern.find(input)?.let {
                val (id, left, top, w, h) = it.destructured
                Claim(id.toInt(), left.toInt(), top.toInt(), w.toInt(), h.toInt())
            } ?: throw IllegalArgumentException("Cannot parse $input")
        }
    }
}