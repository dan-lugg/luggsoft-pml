package com.luggsoft.pml.core

import com.luggsoft.pml.Selector
import org.intellij.lang.annotations.Language

/**
 * TODO
 *
 */
interface SelectorParser
{
    /**
     * Parses the [query] into a [com.luggsoft.pml.Selector].
     *
     * @param query
     * @return
     */
    @Throws(SelectorEvaluationException::class)
    fun parse(@Language("textmate") query: String): Selector
}
