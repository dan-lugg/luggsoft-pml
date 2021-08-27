package com.luggsoft.pml.examples.example1.nodes

import com.luggsoft.pml.PmlName

sealed class Node
{
    abstract val childNodes: List<Node>
}

@PmlName("ANode")
data class ANode(
    override val childNodes: List<Node> = emptyList(),
) : Node()

@PmlName("BNode")
data class BNode(
    override val childNodes: List<Node> = emptyList(),
) : Node()

@PmlName("CNode")
data class CNode(
    override val childNodes: List<Node> = emptyList(),
) : Node()

@PmlName("DNode")
data class DNode(
    override val childNodes: List<Node> = emptyList(),
) : Node()
