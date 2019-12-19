package com.shifthackz.benzinapp.di.component

import com.shifthackz.benzinapp.di.module.InteractorModule
import com.shifthackz.benzinapp.di.scope.InteractorScope
import com.shifthackz.benzinapp.usecases.Interactor
import dagger.Component

@InteractorScope
@Component(modules = [InteractorModule::class], dependencies = [RepositoryComponent::class])
interface InteractorComponent {
    val interactor: Interactor
}