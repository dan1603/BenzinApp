package com.shifthackz.benzinapp.di.component

import com.shifthackz.benzinapp.di.module.RepositoryModule
import com.shifthackz.benzinapp.di.scope.RepositoryScope
import com.shifthackz.benzinapp.usecases.repository.AppRepository
import dagger.Component

@RepositoryScope
@Component(modules = [RepositoryModule::class], dependencies = [ApiComponent::class, DatabaseComponent::class])
interface RepositoryComponent {
    val repository: AppRepository
}