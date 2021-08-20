package com.luggsoft.pml

import org.intellij.lang.annotations.Language

/**
 * This annotation indicates the name used to identify this node type in a PML query.
 *
 * ```
 * @PmlName("MyNode")
 * class MyNode { ... }
 * ```
 *
 * @sample [com.luggsoft.pml.PmlNameExample]
 *
 * @see [com.luggsoft.pml.PmlQuery]
 *
 * @property name
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
@MustBeDocumented
annotation class PmlName(
    @Language("textmate")
    val name: String,
)
