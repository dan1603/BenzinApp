package com.shifthackz.benzinapp.di.component

import com.shifthackz.benzinapp.di.module.DatabaseModule
import com.shifthackz.benzinapp.usecases.repository.database.AppDatabase
import dagger.Component

@Component(modules = [DatabaseModule::class])
interface DatabaseComponent {
    val database: AppDatabase
}
