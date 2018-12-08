/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

/**
 * Advent of Code 2018, Day 8 - Memory Maneuver
 *
 * Problem Description: http://adventofcode.com/2018/day/8
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2018/day8/
 */
package com.ginsberg.advent2018

class Day08(rawInput: String) {
    private val tree: Node = Node.of(rawInput.split(" ").map { it.toInt() }.iterator())

    fun solvePart1(): Int =
        tree.metadataTotal

    fun solvePart2(): Int =
        tree.value

    private class Node(children: List<Node>, metadata: List<Int>) {
        companion object {
            fun of(values: Iterator<Int>): Node {
                val numChildren: Int = values.next()
                val numMetadata: Int = values.next()
                val children = (0 until numChildren).map { Node.of(values) }
                val metadata = (0 until numMetadata).map { values.next() }.toList()
                return Node(children, metadata)
            }
        }

        val metadataTotal: Int =
            metadata.sum() + children.sumBy { it.metadataTotal }

        val value: Int =
            if (children.isEmpty()) metadata.sum()
            else metadata.sumBy { children.getOrNull(it - 1)?.value ?: 0 }

    }
}