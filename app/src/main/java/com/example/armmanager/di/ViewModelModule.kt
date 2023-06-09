package com.example.armmanager.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.armmanager.ui.account.AccountViewModel
import com.example.armmanager.ui.auth.AuthorizationViewModel
import com.example.armmanager.ui.product.ProductViewModel
import com.example.armmanager.ui.register.RegisterViewModel
import com.example.armmanager.ui.request.RequestViewModel
import com.example.armmanager.viewmodel.ArmViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AccountViewModel::class)
    abstract fun bindUserViewModel(userViewModel: AccountViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RequestViewModel::class)
    abstract fun bindRequestViewModel(requestViewModel: RequestViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductViewModel::class)
    abstract fun bindProductViewModel(productViewModel: ProductViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthorizationViewModel::class)
    abstract fun bindAuthorizationViewModel(authorizationViewModel: AuthorizationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    abstract fun bindRegisterViewModel(registerViewModel: RegisterViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ArmViewModelFactory): ViewModelProvider.Factory
}