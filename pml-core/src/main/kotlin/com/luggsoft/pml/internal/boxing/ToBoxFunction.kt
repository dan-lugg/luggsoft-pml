package com.luggsoft.pml

import com.luggsoft.pml.internal.boxing.BoxFunction
import com.luggsoft.pml.internal.boxing.toBox
import kotlin.reflect.KFunction

fun KFunction<*>.toBoxFunction(): BoxFunction = BoxFunction { argumentBoxes ->
    return@BoxFunction this@toBoxFunction
        .call(args = argumentBoxes.toTypedArray())
        .toBox()
}
