package com.shifthackz.benzinapp.di.module

import android.app.Application
import com.shifthackz.benzinapp.App
import com.shifthackz.benzinapp.di.scope.ViewModelScope
import com.shifthackz.benzinapp.domain.*
import com.shifthackz.benzinapp.usecases.Interactor
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule(app: App) {

    internal var app: Application = app

    @ViewModelScope
    @Provides
    internal fun providesAllWorkersViewModel(interactor: Interactor): AllWorkersViewModel {
        return AllWorkersViewModel(app, interactor)
    }

    @ViewModelScope
    @Provides
    internal fun providesSingleUserViewModel(interactor: Interactor): SingleWorkerViewModel {
        return SingleWorkerViewModel(app, interactor)
    }

    @ViewModelScope
    @Provides
    internal fun providesAllBenzinsViewModel(interactor: Interactor): AllBenzinsViewModel {
        return AllBenzinsViewModel(app, interactor)
    }

    @ViewModelScope
    @Provides
    internal fun providesSingleBenzinViewModel(interactor: Interactor): SingleBenzinViewModel {
        return SingleBenzinViewModel(app, interactor)
    }

    @ViewModelScope
    @Provides
    internal fun providesAllHistoryViewModel(interactor: Interactor): AllHistoryViewModel {
        return AllHistoryViewModel(app, interactor)
    }

    @ViewModelScope
    @Provides
    internal fun providesSingleHistoryViewModel(interactor: Interactor): SingleHistoryViewModel {
        return SingleHistoryViewModel(app, interactor)
    }
}