package com.luggsoft.pml.core

import com.luggsoft.pml.Selector

interface SelectorRegistry<THandler> : Iterable<Map.Entry<Selector, Class<THandler>>>
{
    fun <TNode : Any> evaluate(nodes: List<TNode>, selectorEvaluator: SelectorEvaluator): Class<THandler>?
}
