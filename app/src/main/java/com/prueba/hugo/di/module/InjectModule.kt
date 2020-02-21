package com.prueba.hugo.di.module


import com.prueba.hugo.repository.DataRepository
import com.prueba.hugo.views.home.car_register.CarRegisterViewModel
import com.prueba.hugo.views.home.check_in.CheckInViewModel
import com.prueba.hugo.views.home.check_out.CheckOutViewModel
import com.prueba.hugo.views.home.pay.PayViewModel
import com.prueba.hugo.views.init.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module


/**
 * Created by Josael Hernandez on 2020-02-20.
 * Contact : josaeljjh@gmail.com
 */

object InjectModule {

    val injectModule: Module = module {
        single { DataRepository() }
        viewModel { SplashViewModel() }
        viewModel { CheckInViewModel(get()) }
        viewModel { CarRegisterViewModel(get()) }
        viewModel { CheckOutViewModel(get())}
        viewModel { PayViewModel(get())}
    }
}