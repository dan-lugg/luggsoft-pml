package com.luggsoft.pml.models

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.luggsoft.pml.PmlName

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "_type")
sealed class TestNode
{
    abstract val childTestNodes: List<TestNode>
}

@PmlName("ATestNode")
data class ATestNode(
    override val childTestNodes: List<TestNode> = emptyList(),
) : TestNode()

@PmlName("BTestNode")
data class BTestNode(
    override val childTestNodes: List<TestNode> = emptyList(),
) : TestNode()

@PmlName("CTestNode")
data class CTestNode(
    override val childTestNodes: List<TestNode> = emptyList(),
) : TestNode()
