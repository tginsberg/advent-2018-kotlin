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