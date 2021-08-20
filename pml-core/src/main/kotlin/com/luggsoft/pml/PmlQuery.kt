package com.luggsoft.pml

import org.intellij.lang.annotations.Language

/**
 * This annotation indicates the PML query used to match this handler type.
 *
 * ```
 * @PmlQuery("~ MyNode")
 * class MyHandler { ... }
 * ```
 *
 * @sample [com.luggsoft.pml.PmlQueryExample]
 *
 * @see [com.luggsoft.pml.PmlName]
 *
 * @property query
 * @property override
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
@MustBeDocumented
annotation class PmlQuery(
    @Language("textmate")
    val query: String,

    val override: Boolean = false,
)
