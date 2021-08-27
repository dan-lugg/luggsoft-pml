package com.luggsoft.pml.examples.example1.nodes

import com.luggsoft.pml.SelectorEngine
import java.io.Writer

class NodeVisitor(
    private val errorWriter: Writer,
    private val selectorEngine: SelectorEngine<NodeHandler<Node>>,
)
{
    fun visitNode(node: Node, ancestorNodes: List<Node> = emptyList())
    {
        this.selectorEngine.evaluate(ancestorNodes + node)
            ?.handleNode(node, ancestorNodes)
            ?: this.errorWriter.appendLine("ERROR: No handler for ${node::class.simpleName}")

        node.childNodes.forEach { childNode ->
            this.visitNode(childNode, ancestorNodes + node)
        }
    }
}
