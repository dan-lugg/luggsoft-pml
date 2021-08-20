package com.luggsoft.pml.internal

import com.luggsoft.pml.Selector
import org.intellij.lang.annotations.Language

/**
 * TODO
 *
 */
interface SelectorParser
{
    /**
     * TODO
     *
     * @param query
     * @return
     */
    fun parse(@Language("textmate") query: String): Selector
}
