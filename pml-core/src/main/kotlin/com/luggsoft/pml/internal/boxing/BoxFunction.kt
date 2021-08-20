package com.luggsoft.pml.internal.boxing

fun interface BoxFunction
{
    fun invoke(argumentBoxes: List<Box<*>>): Box<*>
}
