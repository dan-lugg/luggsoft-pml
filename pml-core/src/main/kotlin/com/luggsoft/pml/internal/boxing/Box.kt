package com.luggsoft.pml.internal.boxing

import java.io.Serializable

/**
 * TODO
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
     * TODO
     *
     * @param name
     * @return
     */
    fun accessMember(name: String): Box<*>

    /**
     * TODO
     *
     * @param name
     * @param argumentBoxes
     * @return
     */
    fun invokeMember(name: String, argumentBoxes: List<Box<*>>): Box<*>
}
