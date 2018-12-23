/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

/**
 * Advent of Code 2018, Day 22 - Mode Maze
 *
 * Problem Description: http://adventofcode.com/2018/day/22
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2018/day22/
 */
package com.ginsberg.advent2018

import java.util.PriorityQueue


class Day22(rawInput: List<String>) {

    private val depth: Int = rawInput.first().substring(7).toInt()
    private val target: Point = Point.of(rawInput.drop(1).first().substring(8))
    private val cave = LazyGrid(target, depth)

    fun solvePart1(): Int =
        cave.riskLevel()

    fun solvePart2(): Int =
        cave.cheapestPath()?.cost ?: throw IllegalStateException("No path?! Really?!")

    private class LazyGrid(val target: Point, val depth: Int) {
        private val erosionLevels = mutableMapOf<Point, Int>()

        fun riskLevel(at: Point = target): Int =
            (0..at.y).flatMap { y ->
                (0..at.x).map { x ->
                    Point(x, y).erosionLevel() % 3
                }
            }.sum()

        fun cheapestPath(to: Point = target): Traversal? {
            val seenMinimumCost: MutableMap<Pair<Point, Tool>, Int> = mutableMapOf(Point.origin to Tool.Torch to 0)
            val pathsToEvaluate = PriorityQueue<Traversal>().apply {
                add(Traversal(Point.origin, Tool.Torch))
            }

            while (pathsToEvaluate.isNotEmpty()) {
                val thisPath = pathsToEvaluate.poll()

                // Found it, and holding the correct tool
                if (thisPath.end == to && thisPath.holding == Tool.Torch) {
                    return thisPath
                }

                // Candidates for our next set of decisions
                val nextSteps = mutableListOf<Traversal>()

                // Move to each neighbor, holding the same tool.
                thisPath.end.cardinalNeighbors(false).forEach { neighbor ->
                    // Add a Traversal for each if we can go there without changing tools
                    if (thisPath.holding in neighbor.validTools()) {
                        // Can keep the tool.
                        nextSteps += thisPath.copy(
                            end = neighbor,
                            cost = thisPath.cost + 1
                        )
                    }
                }

                // Stay where we are and switch tools to anything we aren't using (but can)
                thisPath.end.validTools().minus(thisPath.holding).forEach { tool ->
                    nextSteps += Traversal(
                        end = thisPath.end,
                        holding = tool,
                        cost = thisPath.cost + 7
                    )
                }

                // Of all possible next steps, add the ones we haven't seen, or have seen and we can now do cheaper.
                nextSteps.forEach { step ->
                    val key = Pair(step.end, step.holding)
                    if (key !in seenMinimumCost || seenMinimumCost.getValue(key) > step.cost) {
                        pathsToEvaluate += step
                        seenMinimumCost[key] = step.cost
                    }
                }
            }
            return null // No path!? Come on...
        }

        private fun Point.erosionLevel(): Int {
            if (this !in erosionLevels) {
                val geo = when {
                    this in erosionLevels -> erosionLevels.getValue(this)
                    this in setOf(Point.origin, target) -> 0
                    y == 0 -> x * 16807
                    x == 0 -> y * 48271
                    else -> left.erosionLevel() * up.erosionLevel()
                }
                erosionLevels[this] = (geo + depth) % 20183
            }
            return erosionLevels.getValue(this)
        }

        private fun Point.validTools(): Set<Tool> =
            when (this) {
                Point.origin -> setOf(Tool.Torch)
                target -> setOf(Tool.Torch)
                else -> Terrain.byErosionLevel(erosionLevel()).tools
            }
    }

    private data class Traversal(val end: Point, val holding: Tool, val cost: Int = 0) : Comparable<Traversal> {

        override fun compareTo(other: Traversal): Int =
            this.cost.compareTo(other.cost)
    }

    private enum class Tool {
        Torch,
        Climbing,
        Neither
    }

    private enum class Terrain(val modVal: Int, val tools: Set<Tool>) {
        Rocky(0, setOf(Tool.Climbing, Tool.Torch)),
        Wet(1, setOf(Tool.Climbing, Tool.Neither)),
        Narrow(2, setOf(Tool.Torch, Tool.Neither));

        companion object {
            val values = arrayOf(Rocky, Wet, Narrow)
            fun byErosionLevel(level: Int): Terrain =
                values.first { it.modVal == level % 3 }
        }

    }
}

