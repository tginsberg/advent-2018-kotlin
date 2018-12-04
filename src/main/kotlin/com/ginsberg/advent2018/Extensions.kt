/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

package com.ginsberg.advent2018

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

