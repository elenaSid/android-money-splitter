package com.elena.moneysplitter

import android.app.Activity
import android.app.Application
import com.elena.moneysplitter.di.Injector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * @author elena
 *         Date: 11.06.2018
 *         Time: 19:37
 */
class MoneySplitterApp : Application(), HasActivityInjector {

    @Inject lateinit var activityDispatchingAndroidInjector:DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        Injector.init(this)
        Injector.get().getAppComponent().inject(this)
    }
}