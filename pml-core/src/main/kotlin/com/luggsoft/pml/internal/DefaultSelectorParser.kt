package com.luggsoft.pml.internal

import com.github.h0tk3y.betterParse.grammar.parseToEnd
import com.luggsoft.pml.Selector
import org.intellij.lang.annotations.Language

/**
 * TODO
 *
 */
class DefaultSelectorParser : SelectorParser
{
    /**
     *
     */
    private val selectorGrammar by lazy(::SelectorGrammar)

    /**
     * TODO
     *
     * @param query
     * @return
     */
    override fun parse(@Language("textmate") query: String): Selector = this.selectorGrammar.parseToEnd(query)
}
