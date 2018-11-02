/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

package com.ginsberg.advent2018

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ResourcesTest {


    @Test
    fun `resourceAsString concatenates lines without delimiter`() {
        assertThat(resourceAsString("read_file_test_1.txt"))
                .isEqualTo("ABC")
    }

    @Test
    fun `resourceAsString concatenates lines with delimiter`() {
        assertThat(resourceAsString(fileName = "read_file_test_1.txt", delimiter = ":::"))
                .isEqualTo("A:::B:::C")
    }

    @Test
    fun `resourceAsList reads lines`() {
        assertThat(resourceAsList("read_file_test_1.txt"))
                .hasSize(3)
                .containsExactly("A", "B", "C")
    }

}