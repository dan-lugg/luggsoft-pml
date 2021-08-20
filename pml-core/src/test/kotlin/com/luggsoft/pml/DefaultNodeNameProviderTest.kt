package com.luggsoft.pml

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class DefaultNodeNameProviderTest
{
    private val nodeNameProvider = DefaultNodeNameProvider()

    @Test
    fun test1()
    {
        val fooName = this.nodeNameProvider.getNodeName(Foo::class.java)
        assertEquals("Foo", fooName)

        val intName = this.nodeNameProvider.getNodeName(Int::class.java)
        assertEquals("int", intName)

        val anyName = this.nodeNameProvider.getNodeName(Any::class.java)
        assertEquals("Object", anyName)
    }

    class Foo
}
