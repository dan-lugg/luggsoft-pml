package com.luggsoft.pml

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlin.random.Random

fun main()
{
    val random = Random(0)
    val objectMapper = jacksonObjectMapper()

    repeat(20) {
        val testNode = buildTestNode(random)
        objectMapper.writeValueAsString(testNode).also(::println)
    }
}
