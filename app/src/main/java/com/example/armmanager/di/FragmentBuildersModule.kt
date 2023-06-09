package com.example.armmanager.di

import com.example.armmanager.ui.account.AccountFragment
import com.example.armmanager.ui.auth.AuthorizationFragment
import com.example.armmanager.ui.request.add.AddProductDialogFragment
import com.example.armmanager.ui.request.add.AddRequestFragment
import com.example.armmanager.ui.request.edit.EditRequestFragment
import com.example.armmanager.ui.request.complete.CompleteRequestFragment
import com.example.armmanager.ui.request.work.WorkRequestFragment
import com.example.armmanager.ui.product.ProductFragment
import com.example.armmanager.ui.register.RegisterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
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

    @ContributesAndroidInjector
    abstract fun contributeAuthorizationFragment(): AuthorizationFragment

    @ContributesAndroidInjector
    abstract fun contributeRegisterFragment(): RegisterFragment

//    @ContributesAndroidInjector
//    abstract fun contributeSearchFragment(): SearchFragment
}