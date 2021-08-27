package com.luggsoft.pml.examples.example1

import com.luggsoft.pml.DefaultSelectorEngine
import com.luggsoft.pml.examples.example1.nodes.ANode
import com.luggsoft.pml.examples.example1.nodes.BNode
import com.luggsoft.pml.examples.example1.nodes.CNode
import com.luggsoft.pml.examples.example1.nodes.DNode
import com.luggsoft.pml.examples.example1.nodes.NodeVisitor
import com.luggsoft.pml.spring.ApplicationContextHandlerFactory
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import java.io.StringWriter

fun main()
{
    val node = ANode(
        childNodes = listOf(
            BNode(),
            BNode(
                childNodes = listOf(
                    ANode(),
                    BNode(),
                    DNode(),
                ),
            ),
            CNode(),
            CNode(
                childNodes = listOf(
                    BNode(),
                    DNode(),
                    BNode(
                        childNodes = listOf(
                            ANode(),
                            CNode(
                                childNodes = listOf(
                                    CNode(),
                                ),
                            ),
                            DNode(),
                        ),
                    ),
                ),
            ),
        ),
    )

    val basePackage = "com.luggsoft.pml.examples"
    val errorWriter = StringWriter()

    val nodeVisitor = NodeVisitor(
        errorWriter = errorWriter,
        selectorEngine = DefaultSelectorEngine.createDefault(
            basePackage = basePackage,
            handlerFactory = ApplicationContextHandlerFactory(
                applicationContext = AnnotationConfigApplicationContext().also { applicationContext ->
                    applicationContext.scan(basePackage)
                    applicationContext.refresh()
                },
            ),
        ),
    )

    nodeVisitor.visitNode(node)

    errorWriter.flush()
    println(errorWriter.toString())
}
