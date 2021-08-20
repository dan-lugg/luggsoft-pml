package com.luggsoft.pml.core

import com.luggsoft.pml.Selector

interface SelectorFormatter
{
    fun formatSelector(selector: Selector): String
}
