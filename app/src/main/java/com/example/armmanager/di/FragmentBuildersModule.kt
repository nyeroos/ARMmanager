package com.example.armmanager.di

import com.example.armmanager.ui.account.AccountFragment
import com.example.armmanager.ui.add.AddProductDialogFragment
import com.example.armmanager.ui.add.AddRequestFragment
import com.example.armmanager.ui.edit.EditRequestFragment
import com.example.armmanager.ui.request.RequestFragment
import com.example.armmanager.ui.request.complete.CompleteRequestFragment
import com.example.armmanager.ui.request.work.WorkRequestFragment
import com.example.armmanager.ui.product.ProductFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeRequestFragment(): RequestFragment

    @ContributesAndroidInjector
    abstract fun contributeCompleteRequestFragment(): CompleteRequestFragment

    @ContributesAndroidInjector
    abstract fun contributeWorkRequestFragment(): WorkRequestFragment

    @ContributesAndroidInjector
    abstract fun contributeAccountFragment(): AccountFragment

    @ContributesAndroidInjector
    abstract fun contributeAddRequestFragment(): AddRequestFragment

    @ContributesAndroidInjector
    abstract fun contributeStatusFragment(): ProductFragment

    @ContributesAndroidInjector
    abstract fun contributeAddProductDialogFragment(): AddProductDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeEditRequestFragment(): EditRequestFragment


//    @ContributesAndroidInjector
//    abstract fun contributeSearchFragment(): SearchFragment
}