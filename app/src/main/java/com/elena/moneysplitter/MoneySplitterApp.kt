package com.elena.moneysplitter

import android.app.Application
import com.elena.moneysplitter.di.Injector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/**
 * @author elena
 */
class MoneySplitterApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        Injector.init(this)
        Injector.getAppComponent().inject(this)
    }


    override fun androidInjector() = androidInjector
}