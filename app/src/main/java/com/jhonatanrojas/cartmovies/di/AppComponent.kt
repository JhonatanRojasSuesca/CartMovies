package com.jhonatanrojas.cartmovies.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivitiesBuilder::class,
        ModuleRepository::class,
        ModuleNetwork::class])
@Singleton
interface AppComponent: AndroidInjector<MoveApplication> {

    fun context(): Context
    fun application():Application

    @Component.Builder
    interface  Builder{

        fun build():AppComponent

        @BindsInstance
        fun application(moveApplication: MoveApplication):Builder
    }
}