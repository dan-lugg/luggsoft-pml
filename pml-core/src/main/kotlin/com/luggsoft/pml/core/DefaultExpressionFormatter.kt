package com.luggsoft.pml.core

import com.luggsoft.pml.core.ast.AccessMemberExpression
import com.luggsoft.pml.core.ast.Expression
import com.luggsoft.pml.core.ast.InvokeMemberExpression
import com.luggsoft.pml.core.ast.RootExpression
import com.luggsoft.pml.core.ast.ScopedExpression
import com.luggsoft.pml.core.ast.ValueExpression
import com.luggsoft.pml.core.boxing.BooleanBox
import com.luggsoft.pml.core.boxing.Box
import com.luggsoft.pml.core.boxing.DoubleBox
import com.luggsoft.pml.core.boxing.FloatBox
import com.luggsoft.pml.core.boxing.IntBox
import com.luggsoft.pml.core.boxing.LongBox
import com.luggsoft.pml.core.boxing.NullBox
import com.luggsoft.pml.core.boxing.ObjectBox
import com.luggsoft.pml.core.boxing.StringBox

class DefaultExpressionFormatter : ExpressionFormatter
{
    override fun formatExpression(expression: Expression): String
    {
        val stringBuilder = StringBuilder()

        when (expression)
        {
            is RootExpression ->
            {
                stringBuilder.append("$")
            }

            is ValueExpression ->
            {
                stringBuilder.append(this.formatBox(expression.valueBox))
            }

            is ScopedExpression ->
            {
                stringBuilder.append(this.formatExpression(expression.expression))
                stringBuilder.append(".")
                stringBuilder.append(this.formatExpression(expression.memberExpression))
            }

            is AccessMemberExpression ->
            {
                stringBuilder.append(expression.name)
            }

            is InvokeMemberExpression ->
            {
                stringBuilder.append(expression.name)
                stringBuilder.append("(")

                expression.argumentExpressions.dropLast(1).forEach { argumentExpression ->
                    stringBuilder.append(this.formatExpression(argumentExpression))
                    stringBuilder.append(", ")
                }

                val argumentExpression = expression.argumentExpressions.lastOrNull()

                if (argumentExpression != null)
                {
                    stringBuilder.append(this.formatExpression(argumentExpression))
                }

                stringBuilder.append(")")
            }
        }

        return stringBuilder.toString()
    }

    private fun formatBox(box: Box<*>): String
    {
        val stringBuilder = StringBuilder()

        when (box)
        {
            is IntBox ->
            {
                stringBuilder.append(box.value)
                stringBuilder.append("i")
            }

            is LongBox ->
            {
                stringBuilder.append(box.value)
                stringBuilder.append("l")
            }

            is FloatBox ->
            {
                stringBuilder.append(box.value)
                stringBuilder.append("f")
            }

            is DoubleBox ->
            {
                stringBuilder.append(box.value)
                stringBuilder.append("d")
            }

            is BooleanBox ->
            {
                stringBuilder.append(box.value)
            }

            is StringBox ->
            {
                stringBuilder.append("'")
                stringBuilder.append(this.escapeString(box.value))
                stringBuilder.append("'")
            }

            is ObjectBox ->
            {
                stringBuilder.append("/* O:${box} */")
            }

            is NullBox ->
            {
                stringBuilder.append("null")
            }
        }

        return stringBuilder.toString()
    }

    private fun escapeString(string: String): String = string
        .replace("\\", "\\\\")
        .replace("'", "\\'")

}
