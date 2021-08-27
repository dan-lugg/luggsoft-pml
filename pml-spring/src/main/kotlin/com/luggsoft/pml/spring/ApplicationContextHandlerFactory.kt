package com.luggsoft.pml.spring

import com.luggsoft.pml.HandlerFactory
import org.springframework.context.ApplicationContext

/**
 * TODO
 *
 * @param THandler
 * @property applicationContext
 */
class ApplicationContextHandlerFactory<THandler>(
    private val applicationContext: ApplicationContext,
) : HandlerFactory<THandler>
{
    /**
     * TODO
     *
     * @param handlerClass
     * @return
     */
    override fun invoke(handlerClass: Class<THandler>): THandler = this.applicationContext.getBean(handlerClass) as THandler
}
