/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

/**
 * Advent of Code 2018, Day 9 - Marble Mania
 *
 * Problem Description: http://adventofcode.com/2018/day/9
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2018/day9/
 */
package com.ginsberg.advent2018

import java.util.LinkedList
import kotlin.math.absoluteValue


class Day09(private val players: Int, private val highest: Int) {

    fun solvePart1(): Long =
        play(players, highest)

    fun solvePart2(): Long =
        play(players, highest * 100)

    private fun play(numPlayers: Int, highest: Int): Long {
        val scores = LongArray(numPlayers)
        val marbles = LinkedList<Int>().also { it.add(0) }

        (1..highest).forEach { marble ->
            when {
                marble % 23 == 0 -> {
                    scores[marble % numPlayers] += marble + with(marbles) {
                        shift(-7)
                        removeFirst().toLong()
                    }
                    marbles.shift(1)
                }
                else -> {
                    with(marbles) {
                        shift(1)
                        addFirst(marble)
                    }
                }
            }
        }
        return scores.max()!!
    }

    private fun <T> LinkedList<T>.shift(n: Int): Unit =
        when {
            n < 0 -> repeat(n.absoluteValue) {
                addLast(removeFirst())
            }
            else -> repeat(n) {
                addFirst(removeLast())
            }
        }

}
