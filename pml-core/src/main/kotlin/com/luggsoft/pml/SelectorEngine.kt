package com.luggsoft.pml

interface SelectorEngine<THandler>
{
    fun <TNode : Any> evaluate(nodes: List<TNode>): THandler?
}
