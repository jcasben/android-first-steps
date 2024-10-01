package com.jcasben.rickmortyapp.di

import com.jcasben.rickmortyapp.data.database.RickMortyDatabase
import com.jcasben.rickmortyapp.data.database.getDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module {
    return module {
        single<RickMortyDatabase> { getDatabase(get()) }
    }
}