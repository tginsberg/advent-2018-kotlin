/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

/**
 * Advent of Code 2018, Day 13 - Mine Cart Madness
 *
 * Problem Description: http://adventofcode.com/2018/day/13
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2018/day13/
 */
package com.ginsberg.advent2018

import java.util.TreeSet

typealias Track = Array<CharArray>

class Day13(rawInput: List<String>) {

    private val track: Track = rawInput.map { it.toCharArray() }.toTypedArray()
    private val carts: Set<Cart> = Cart.findAll(track)

    fun solvePart1(): Point =
        collisions().first()

    fun solvePart2(): Point {
        collisions().toList() // Consume the entire sequence
        return carts.first { it.alive }.run { Point(this.x, this.y) }
    }


    private fun collisions(): Sequence<Point> = sequence {
        while (carts.count { it.alive } > 1) {
            carts.sorted().forEach { cart ->
                if (cart.alive) {
                    cart.move(track)

                    // If we collided, mark ourselves and the cart we collided with as not alive
                    // yield the crash site.
                    carts.firstOrNull { cart.collidesWith(it) }?.let { otherCart ->
                        cart.alive = false
                        otherCart.alive = false
                        yield(Point(cart.x, cart.y))
                    }
                }
            }
        }
    }

    private data class Cart(var x: Int, var y: Int,
                            var direction: Direction, var turn: Turn = Turn.Left,
                            var alive: Boolean = true) : Comparable<Cart> {

        companion object {
            fun findAll(theTrack: Track): Set<Cart> =
                theTrack.mapIndexed { y, row ->
                    row.mapIndexed { x, spot ->
                        if (spot in setOf('>', '<', '^', 'v')) {
                            Cart(x, y, Direction(spot))
                        } else null
                    }
                }
                    .flatten()
                    .filterNotNull()
                    .toCollection(TreeSet())
        }

        fun collidesWith(other: Cart): Boolean =
            this != other && this.alive && other.alive && x == other.x && y == other.y

        fun move(track: Track) {
            // Move in the direction we are facing
            x += direction.dx
            y += direction.dy

            // Handle turning, anything else is movement in the same direction the next time through.
            when (track[y][x]) {
                // Interchange rules
                '+' -> {
                    direction = direction.turn(turn)
                    turn = turn.next
                }
                // Turn
                '\\' -> {
                    direction = when (direction) {
                        Direction.North, Direction.South -> direction.left
                        else -> direction.right
                    }
                }
                // Turn
                '/' -> {
                    direction = when (direction) {
                        Direction.East, Direction.West -> direction.left
                        else -> direction.right
                    }
                }
            }
        }

        override fun compareTo(other: Cart): Int =
            when {
                x < other.x -> -1
                x > other.x -> 1
                y < other.y -> -1
                y > other.y -> 1
                else -> 0
            }
    }

    private sealed class Turn {
        abstract val next: Turn

        object Left : Turn() {
            override val next = Center
        }

        object Center : Turn() {
            override val next = Right
        }

        object Right : Turn() {
            override val next = Left
        }
    }

    private sealed class Direction {

        companion object {
            operator fun invoke(id: Char): Direction =
                when (id) {
                    '^' -> North
                    'v' -> South
                    '>' -> East
                    '<' -> West
                    else -> throw IllegalArgumentException("No such direction $id")
                }
        }

        abstract val left: Direction
        abstract val right: Direction
        abstract val dx: Int
        abstract val dy: Int

        fun turn(turn: Turn): Direction =
            when (turn) {
                Turn.Left -> this.left
                Turn.Center -> this
                Turn.Right -> this.right
            }


        object North : Direction() {
            override val left = West
            override val right = East
            override val dx = 0
            override val dy = -1
        }

        object South : Direction() {
            override val left = East
            override val right = West
            override val dx = 0
            override val dy = 1
        }

        object East : Direction() {
            override val left = North
            override val right = South
            override val dx = 1
            override val dy = 0
        }

        object West : Direction() {
            override val left = South
            override val right = North
            override val dx = -1
            override val dy = 0
        }
    }


}
