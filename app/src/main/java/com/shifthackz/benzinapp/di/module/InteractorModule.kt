package com.shifthackz.benzinapp.di.module

import com.shifthackz.benzinapp.di.scope.InteractorScope
import com.shifthackz.benzinapp.usecases.Interactor
import com.shifthackz.benzinapp.usecases.repository.AppRepository
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {
    @InteractorScope
    @Provides
    internal fun providesInteractor(repository: AppRepository): Interactor {
        return Interactor(repository)
    }
}