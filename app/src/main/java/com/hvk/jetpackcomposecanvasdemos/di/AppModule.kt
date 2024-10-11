package com.hvk.jetpackcomposecanvasdemos.di

import com.hvk.jetpackcomposecanvasdemos.BottleViewModel
import com.hvk.jetpackcomposecanvasdemos.DefaultNavigator
import com.hvk.jetpackcomposecanvasdemos.Destination
import com.hvk.jetpackcomposecanvasdemos.MainViewModel
import com.hvk.jetpackcomposecanvasdemos.Navigator
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single<Navigator> {
        DefaultNavigator(startDestination = Destination.HomeGraph)
    }

    viewModelOf(::MainViewModel)
    viewModelOf(::BottleViewModel)
}