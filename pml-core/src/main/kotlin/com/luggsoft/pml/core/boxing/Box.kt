package com.luggsoft.pml.core.boxing

import java.io.Serializable

/**
 * Represents a boxed Kotlin value.
 *
 * @param TValue
 */
interface Box<TValue : Any> : Comparable<Box<*>>, Serializable
{
    /**
     *
     */
    val value: TValue

    /**
     *
     */
    val clazz: Class<out TValue>

    /**
     * Accesses the member named [name].
     * Returns the value as a [Box].
     *
     * @param name
     * @return
     */
    fun accessMember(name: String): Box<*>

    /**
     * Invokes the member named [name] with the [argumentBoxes] as arguments.
     * Returns the return value as a [Box].
     *
     * @param name
     * @param argumentBoxes
     * @return
     */
    fun invokeMember(name: String, argumentBoxes: List<Box<*>>): Box<*>
}
