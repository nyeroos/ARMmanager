package com.example.armmanager.di

import com.example.armmanager.ui.account.AccountFragment
import com.example.armmanager.ui.add.AddRequestFragment
import com.example.armmanager.ui.request.RequestFragment
import com.example.armmanager.ui.status.StatusFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeRequestFragment(): RequestFragment

    @ContributesAndroidInjector
    abstract fun contributeAccountFragment(): AccountFragment

    @ContributesAndroidInjector
    abstract fun contributeAddRequestFragment(): AddRequestFragment

    @ContributesAndroidInjector
    abstract fun contributeStatusFragment(): StatusFragment

//    @ContributesAndroidInjector
//    abstract fun contributeSearchFragment(): SearchFragment
}