/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

/**
 * Advent of Code 2018, Day 18 - Settlers of The North Pole
 *
 * Problem Description: http://adventofcode.com/2018/day/18
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2018/day18/
 */
package com.ginsberg.advent2018

import java.util.Arrays

typealias Forest = Array<CharArray>

class Day18(rawInput: List<String>) {

    private val forest: Forest = rawInput.map { it.toCharArray() }.toTypedArray()

    fun solvePart1(): Int =
        growTrees().drop(9).first().value()


    fun solvePart2(find: Int = 1_000_000_000): Int {
        val seen = mutableMapOf<Int, Int>()
        var generation = 0
        val firstRepeating = growTrees().dropWhile { tree ->
            val treeHash = tree.hash()
            generation++
            if (treeHash in seen) {
                false
            } else {
                seen[treeHash] = generation
                true
            }
        }.first()

        val cycleLength = generation - seen[firstRepeating.hash()]!!
        val remainingSteps = (find - generation) % cycleLength

        // Fast forward to the target
        return growTrees(firstRepeating).drop(remainingSteps - 1).first().value()
    }

    private fun growTrees(initial: Forest = forest): Sequence<Forest> = sequence {
        var current = initial
        while (true) {
            current = current.nextGeneration()
            yield(current)
        }
    }

    private fun Forest.nextGeneration(): Array<CharArray> =
        this.mapIndexed { y, row ->
            row.mapIndexed { x, _ -> this.nextGenerationSpot(Point(x, y)) }.toCharArray()
        }.toTypedArray()

    private fun Forest.nextGenerationSpot(at: Point): Char {
        val neighborMap = this.countNeighbors(at)
        return when (val space = this[at]) {
            OPEN -> if (neighborMap.getOrDefault(TREE, 0) >= 3) TREE else OPEN
            TREE -> if (neighborMap.getOrDefault(LUMBERYARD, 0) >= 3) LUMBERYARD else TREE
            LUMBERYARD -> if (neighborMap.getOrDefault(LUMBERYARD, 0) >= 1 &&
                neighborMap.getOrDefault(TREE, 0) >= 1) LUMBERYARD else OPEN
            else -> space
        }
    }

    private fun Forest.countNeighbors(at: Point): Map<Char, Int> =
        at.neighbors(false)
            .filterNot { it.x >= this[0].size }
            .filterNot { it.y >= this.size }
            .map { this[it] }
            .groupingBy { it }
            .eachCount()

    private fun Forest.value(): Int =
        this.flatMap { row ->
            row.map { it }
        }
            .groupingBy { it }
            .eachCount()
            .run {
                getOrDefault(TREE, 0) * getOrDefault(LUMBERYARD, 0)
            }


    private fun Forest.hash(): Int =
        Arrays.deepHashCode(this)

    companion object {
        private const val OPEN = '.'
        private const val LUMBERYARD = '#'
        private const val TREE = '|'
    }

}
