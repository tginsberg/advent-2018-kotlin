/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

package com.ginsberg.advent2018

import java.io.File

internal object Resources {
    fun resourceAsString(fileName: String, delimiter: String = ""): String =
            File(Resources.javaClass.classLoader.getResource(fileName).toURI())
                    .readLines()
                    .reduce { a, b -> "$a$delimiter$b" }

    fun resourceAsList(fileName: String): List<String> =
            File(Resources.javaClass.classLoader.getResource(fileName).toURI())
                    .readLines()
}

