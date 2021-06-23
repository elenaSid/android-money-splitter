package com.elena.domain.common

/**
 * @author elena
 */
interface KeyValueStorage {

    /**
     * Проверяет наличие [key] в хранилище
     */
    fun has(key: String): Boolean

    /**
     * Удаляет [key] из хранилища
     */
    fun remove(key: String)

    /**
     * Возвращает строковое значение по [key] из хранилища, если онон не найдено - то [defaultValue]
     */
    fun getValue(key: String, defaultValue: String?): String?

    /**
     * Устанавливает строковое [value] в хранилище под указанным [key]
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