package com.luggsoft.pml

import com.luggsoft.pml.core.boxing.BoxFunction
import com.luggsoft.pml.core.boxing.toBox
import kotlin.reflect.KFunction

fun KFunction<*>.toBoxFunction(): BoxFunction = BoxFunction { argumentBoxes ->
    return@BoxFunction this@toBoxFunction
        .call(args = argumentBoxes.toTypedArray())
        .toBox()
}
