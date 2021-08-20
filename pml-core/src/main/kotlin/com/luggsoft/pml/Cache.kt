package com.luggsoft.pml

interface Cache<TKey, TValue>
{
    fun has(key: TKey): Boolean

    fun get(key: TKey): TValue?

    fun put(key: TKey, value: TValue): TValue?

    companion object
    {
        fun <TKey, TValue> createNullCache(): Cache<TKey, TValue> = object : Cache<TKey, TValue>
        {
            override fun has(key: TKey): Boolean = false

            override fun get(key: TKey): TValue? = null

            override fun put(key: TKey, value: TValue): TValue? = null
        }
    }
}
