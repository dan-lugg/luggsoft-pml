package com.luggsoft.pml.core.boxing

fun interface BoxFunction
{
    fun invoke(argumentBoxes: List<Box<*>>): Box<*>
}
