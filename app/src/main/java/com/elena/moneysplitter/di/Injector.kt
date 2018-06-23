package com.elena.moneysplitter.di

import android.content.Context

/**
 * @author elena
 *         Date: 11.06.2018
 *         Time: 19:54
 */
class Injector(appContext: Context) {
    private var appComponent: AppComponent

    companion object {
        @JvmStatic private lateinit var instance: Injector

        /**
         * Инициализирует экземпляр класса-помощника для внедрения зависимостей и основной компонент приложения
         *
         * @param appContext [Context]
         */
        @JvmStatic fun init(appContext: Context) {
            instance = Injector(appContext)
        }

        /**
         * Возвращает экземпляр класса-помощника для внедрения зависимостей
         *
         * @return [Injector]
         */
        fun get(): Injector {
            return instance
        }
    }

    /**
     * Инициализирует компонент приложения
     *
     * @param appContext [Context]
     * @return [AppComponent]
     */
    private fun buildAppComponent(appContext: Context): AppComponent {
        return DaggerAppComponent.builder().context(appContext).build()
    }

    /**
     * Возвращает компонент приложения
     *
     * @return [AppComponent]
     */
    fun getAppComponent(): AppComponent {
        return appComponent
    }

    init {
        appComponent = buildAppComponent(appContext)
    }
}