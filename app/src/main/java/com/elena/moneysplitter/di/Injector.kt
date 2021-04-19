package com.elena.moneysplitter.di

import android.app.Application

/**
 * Класс-помощник для внедрения зависимостей
 *
 * @author elena
 */
object Injector {

    private var appComponent: AppComponent? = null

    fun init(application: Application) {
        appComponent = buildAppComponent(application)
    }

    /**
     * Возвращает компонент приложения
     */
    fun getAppComponent() = requireNotNull(appComponent, { "AppComponent not initialized" })

    /**
     * Инициализирует компонент приложения
     */
    private fun buildAppComponent(application: Application) =
            DaggerAppComponent.builder().app(application).build()
}