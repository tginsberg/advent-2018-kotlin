/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

/**
 * Advent of Code 2018, Day 7 - The Sum of Its Parts
 *
 * Problem Description: http://adventofcode.com/2018/day/7
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2018/day7/
 */
package com.ginsberg.advent2018

class Day07(input: List<String>) {

    private val allPairs = parseInput(input)
    private val dependedOn: Map<Char, Set<Char>> = generateDependencies(allPairs.map { it.second to it.first })
    private val dependedBy: Map<Char, Set<Char>> = generateDependencies(allPairs)
    private val allKeys = dependedBy.keys.union(dependedOn.keys)

    fun solvePart1(): String {
        val ready = allKeys.filterNot { it in dependedOn }.toMutableList()
        val done = mutableListOf<Char>()
        while (ready.isNotEmpty()) {
            val next = ready.sorted().first().also { ready.remove(it) }
            done.add(next)
            dependedBy[next]?.let { maybeReadySet ->
                ready.addAll(maybeReadySet.filter { maybeReady ->
                    dependedOn.getValue(maybeReady).all { it in done }
                })
            }
        }
        return done.joinToString(separator = "")
    }

    fun solvePart2(workers: Int, costFunction: (Char) -> Int): Int {
        val ready = allKeys.filterNot { it in dependedOn }.map { it to costFunction(it) }.toMutableList()
        val done = mutableListOf<Char>()
        var time = 0

        while (ready.isNotEmpty()) {
            // Work on things that are ready.
            // Do one unit of work per worker, per item at the head of the queue.
            ready.take(workers).forEachIndexed { idx, work ->
                ready[idx] = Pair(work.first, work.second - 1)
            }

            // These are done
            ready.filter { it.second == 0 }.forEach { workItem ->
                done.add(workItem.first)

                // Now that we are done, add some to ready that have become unblocked
                dependedBy[workItem.first]?.let { maybeReadySet ->
                    ready.addAll(
                        maybeReadySet.filter { maybeReady ->
                            dependedOn.getValue(maybeReady).all { it in done || it == workItem.first }
                        }
                            .map { it to costFunction(it) }
                            .sortedBy { it.first }
                    )
                }
            }

            // Remove anything that we don't need to look at anymore.
            ready.removeIf { it.second == 0 }

            time++
        }
        return time
    }

    private fun parseInput(input: List<String>): List<Pair<Char, Char>> =
        input.map { row ->
            row.split(" ").run { this[1].first() to this[7].first() }
        }

    private fun generateDependencies(input: List<Pair<Char, Char>>): Map<Char, Set<Char>> =
        input
            .groupBy { it.first }
            .mapValues { (_, value) -> value.map { it.second }.toSet() }

    companion object {
        fun exampleCostFunction(c: Char): Int = c - 'A' + 1
        fun actualCostFunction(c: Char): Int = 60 + (c - 'A' + 1)
    }

}