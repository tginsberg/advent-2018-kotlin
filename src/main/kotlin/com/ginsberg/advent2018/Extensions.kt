/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

package com.ginsberg.advent2018

import kotlin.math.absoluteValue

/**
 * Constantly loop through the given List<T>, turning it into a sequence
 * that does not end.
 */
fun <T> List<T>.toInfiniteSequence(): Sequence<T> = sequence {
    if (this@toInfiniteSequence.isEmpty()) {
        return@sequence
    }
    while (true) {
        yieldAll(this@toInfiniteSequence)
    }
}

/**
 * Find the most commonly occurring element in the Iterable<T>
 */
fun <T> Iterable<T>.mostFrequent(): T? =
    this.groupBy { it }.maxBy { it.value.size }?.key

/**
 * Find the absolute span of this IntRange.
 * This is a property rather than a function because the answer cannot change.
 */
val IntRange.span: Long
    get() =
        (this.last.toLong() - this.first.toLong()).absoluteValue

operator fun Array<CharArray>.get(point: Point): Char =
    this[point.y][point.x]

operator fun Array<CharArray>.set(point: Point, to: Char) {
    this[point.y][point.x] = to
}

operator fun Array<CharArray>.contains(point: Point): Boolean =
    point.x >= 0 && point.x < this[0].size && point.y >= 0 && point.y < this.size
