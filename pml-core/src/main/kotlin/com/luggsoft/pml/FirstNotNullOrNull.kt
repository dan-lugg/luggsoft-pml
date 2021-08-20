package com.luggsoft.pml

fun <TElement, TResult> Iterable<TElement>.firstNotNullOrNull(block: (TElement) -> TResult?): TResult?
{
    for (element in this)
    {
        return block.invoke(element) ?: continue
    }

    return null
}
