package com.jcasben.rickmortyapp.di

import com.jcasben.rickmortyapp.domain.GetRandomCharacterUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetRandomCharacterUseCase)
}