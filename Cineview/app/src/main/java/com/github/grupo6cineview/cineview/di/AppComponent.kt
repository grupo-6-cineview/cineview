package com.github.grupo6cineview.cineview.di

import com.github.grupo6cineview.cineview.di.AppModule.viewModelModules
import com.github.grupo6cineview.cineview.di.DataModule.daoModules
import com.github.grupo6cineview.cineview.di.DataModule.mapperModules
import com.github.grupo6cineview.cineview.di.DataModule.repositoryModules
import com.github.grupo6cineview.cineview.di.DomainModule.useCaseModules
import org.koin.core.module.Module

object AppComponent {

    val allModules: List<Module> get() =
        listOf(
            *allAppModules,
            *allDomainModules,
            *allDataModules
        )

    private val allAppModules: Array<Module> get() = arrayOf(viewModelModules)
    private val allDomainModules: Array<Module> get() = arrayOf(useCaseModules)
    private val allDataModules: Array<Module> get() = arrayOf(repositoryModules, daoModules, mapperModules)
}