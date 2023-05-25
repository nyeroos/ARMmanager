package com.example.armmanager

import android.app.Application
import androidx.fragment.app.Fragment
import com.example.armmanager.di.AppInjector
import com.example.armmanager.di.DaggerAppComponent
import com.google.android.material.color.DynamicColors
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class ArmApp: Application(),HasAndroidInjector {


    @Inject lateinit var androidInjector : DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
        DynamicColors.applyToActivitiesIfAvailable(this);
    }


}