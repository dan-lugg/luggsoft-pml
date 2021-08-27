package com.luggsoft.pml.examples.example1.nodes

import com.luggsoft.pml.PmlQuery
import com.luggsoft.pml.examples.util.prindentln
import org.springframework.stereotype.Component

sealed class NodeHandler<TNode : Node>
{
    abstract fun handleNode(node: TNode, ancestorNodes: List<Node>)
}

@Component
@PmlQuery("~ ANode")
class ANodeHandler : NodeHandler<ANode>()
{
    override fun handleNode(node: ANode, ancestorNodes: List<Node>)
    {
        prindentln(ancestorNodes.size, "ANodeHandler (~A) : ${node::class.simpleName}")
    }
}

@Component
@PmlQuery("~ BNode")
class BNodeHandler : NodeHandler<BNode>()
{
    override fun handleNode(node: BNode, ancestorNodes: List<Node>)
    {
        prindentln(ancestorNodes.size, "BNodeHandler (~B) : ${node::class.simpleName}")
    }
}

sealed class CNodeHandler : NodeHandler<CNode>()

@Component
@PmlQuery("~ ANode > CNode")
class InsideACNodeHandler : CNodeHandler()
{
    override fun handleNode(node: CNode, ancestorNodes: List<Node>)
    {
        prindentln(ancestorNodes.size, "InsideACNodeHandler (~A>C) : ${node::class.simpleName}")
    }
}

@Component
@PmlQuery("~ BNode > CNode")
class InsideBCNodeHandler : CNodeHandler()
{
    override fun handleNode(node: CNode, ancestorNodes: List<Node>)
    {
        prindentln(ancestorNodes.size, "InsideBCNodeHandler (~B>C) : ${node::class.simpleName}")
    }
}
