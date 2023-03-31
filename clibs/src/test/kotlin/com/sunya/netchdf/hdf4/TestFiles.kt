package com.sunya.netchdf.hdf4

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import test.util.testData
import test.util.testFilesIn
import java.util.stream.Stream

class TestFiles {

    companion object {
        @JvmStatic
        fun params(): Stream<Arguments> {
            val hdf4 =
                testFilesIn(testData + "hdf4")
                    .withRecursion()
                    .addNameFilter { name -> !name.endsWith(".cdl") }
                    .addNameFilter { name -> !name.endsWith(".jpg") }
                    .addNameFilter { name -> !name.endsWith(".gif") }
                    .addNameFilter { name -> !name.endsWith(".ncml") }
                    .addNameFilter { name -> !name.endsWith(".png") }
                    .addNameFilter { name -> !name.endsWith(".pdf") }
                    .addNameFilter { name -> !name.endsWith(".tif") }
                    .addNameFilter { name -> !name.endsWith(".tiff") }
                    .addNameFilter { name -> !name.endsWith(".txt") }
                    .addNameFilter { name -> !name.endsWith(".xml") }
                    .build()

            val devcdm = testFilesIn(testData + "devcdm/hdf4")
                .withRecursion()
                .build()

            val cdmUnitTest = testFilesIn(testData + "cdmUnitTest/formats/hdf4")
                    .withRecursion()
                .addNameFilter { name -> !name.endsWith(".cdl") }
                .addNameFilter { name -> !name.endsWith(".jpg") }
                .addNameFilter { name -> !name.endsWith(".gif") }
                .addNameFilter { name -> !name.endsWith(".ncml") }
                .addNameFilter { name -> !name.endsWith(".png") }
                .addNameFilter { name -> !name.endsWith(".pdf") }
                .addNameFilter { name -> !name.endsWith(".tif") }
                .addNameFilter { name -> !name.endsWith(".tiff") }
                .addNameFilter { name -> !name.endsWith(".txt") }
                .addNameFilter { name -> !name.endsWith(".xml") }
                .build()

            return Stream.of(devcdm, hdf4).flatMap { i -> i}
        }

        val filenames = mutableMapOf<String, MutableList<String>>()

        @JvmStatic
        @AfterAll
        fun afterAll() {
            println("*** nfiles = ${filenames.size}")
            filenames.keys.sorted().forEach {
                val paths = filenames[it]!!
                if (paths.size > 1) {
                    println("$it")
                    paths.forEach { println("  $it") }
                }
            }
        }
    }

    @ParameterizedTest
    @MethodSource("params")
    fun sortFilenames(filename: String) {
        val path = filename.substringBeforeLast("/")
        val name = filename.substringAfterLast("/")
        val paths = filenames.getOrPut(name) { mutableListOf() }
        paths.add(path)
    }
}