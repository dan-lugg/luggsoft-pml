package com.luggsoft.pml.internal.boxing

fun Any?.toBox(): Box<*> = when (this)
{
    null -> NullBox
    is Int -> IntBox(value = this)
    is Long -> LongBox(value = this)
    is Float -> FloatBox(value = this)
    is Double -> DoubleBox(value = this)
    is Boolean -> BooleanBox(value = this)
    is String -> StringBox(value = this)
    is Box<*> -> this
    else -> ObjectBox(value = this)
}

fun Any?.toIntBox(): IntBox = this.toBox() as IntBox

fun Any?.toLongBox(): LongBox = this.toBox() as LongBox

fun Any?.toFloatBox(): FloatBox = this.toBox() as FloatBox

fun Any?.toDoubleBox(): DoubleBox = this.toBox() as DoubleBox

fun Any?.toBooleanBox(): BooleanBox = this.toBox() as BooleanBox

fun Any?.toStringBox(): StringBox = this.toBox() as StringBox

fun Any?.toObjectBox(): Box<Any> = TODO()
