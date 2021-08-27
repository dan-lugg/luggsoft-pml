package com.luggsoft.pml

import com.luggsoft.pml.core.boxing.Box
import com.luggsoft.pml.core.boxing.NamedBoxFunction
import com.luggsoft.pml.core.boxing.toBox
import kotlin.reflect.KFunction

fun KFunction<*>.toNamedBoxFunction(): NamedBoxFunction = object : NamedBoxFunction
{
    override val name: String = this@toNamedBoxFunction.name

    override fun invoke(argumentBoxes: List<Box<*>>): Box<*> = this@toNamedBoxFunction
        .call(args = argumentBoxes.toTypedArray())
        .toBox()
}
