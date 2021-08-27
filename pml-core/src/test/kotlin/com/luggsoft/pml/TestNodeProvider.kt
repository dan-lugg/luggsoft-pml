package com.luggsoft.pml

import com.luggsoft.pml.models.TestNode

interface TestNodeProvider
{
    fun getTestNode(): TestNode
}
