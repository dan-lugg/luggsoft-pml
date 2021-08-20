package com.luggsoft.pml.core

import com.luggsoft.pml.core.ast.Expression

interface ExpressionFormatter
{
    fun formatExpression(expression: Expression): String
}
