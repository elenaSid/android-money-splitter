package com.elena.moneysplitter.domain.common

/**
 * @author elena
 *         Date: 11.06.2018
 *         Time: 20:28
 */
interface KeyValueStorage {

    /**
     * Проверяет наличие ключа в хранилище
     *
     * @param key ключ
     * @return истина, если ключ найден
     */
    fun has(key: String): Boolean

    /**
     * Удаляет ключ из хранилища
     *
     * @param key ключ
     */
    fun remove(key: String)

    /**
     * Возвращает строковое значение из хранилища
     *
     * @param key ключ
     * @param defaultValue значение по-умолчанию
     * @return значение или значение по-умолчанию
     */
    fun getValue(key: String, defaultValue: String?): String?

    /**
     * Устанавливает строковое значение в хранилище
     *
     * @param key ключ
     * @param value значение
     */
    fun setValue(key: String, value: String?)

    /**
     * @see .getValue
     */
    fun getValue(key: String, defaultValue: Int): Int

    /**
     * @see .setValue
     */
    fun setValue(key: String, value: Int)

    /**
     * @see .getValue
     */
    fun getValue(key: String, defaultValue: Boolean): Boolean

    /**
     * @see .setValue
     */
    fun setValue(key: String, value: Boolean)

    /**
     * @see .getValue
     */
    fun getValue(key: String, defaultValue: Long): Long

    /**
     * @see .setValue
     */
    fun setValue(key: String, value: Long)
}