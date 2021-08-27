package com.luggsoft.pml.examples.util

fun Int.toIndent(symbol: String = "\t") = symbol.repeat(this)

fun prindentln(size: Int, message: Int) = println("${size.toIndent()}$message")

fun prindentln(size: Int, message: Any?) = println("${size.toIndent()}$message")

fun prindentln(size: Int, message: Byte) = println("${size.toIndent()}$message")

fun prindentln(size: Int, message: Char) = println("${size.toIndent()}$message")

fun prindentln(size: Int, message: Long) = println("${size.toIndent()}$message")

fun prindentln(size: Int, message: Float) = println("${size.toIndent()}$message")

fun prindentln(size: Int, message: Short) = println("${size.toIndent()}$message")

fun prindentln(size: Int, message: Double) = println("${size.toIndent()}$message")

fun prindentln(size: Int, message: Boolean) = println("${size.toIndent()}$message")

fun prindentln(size: Int, message: CharArray) = println("${size.toIndent()}$message")
