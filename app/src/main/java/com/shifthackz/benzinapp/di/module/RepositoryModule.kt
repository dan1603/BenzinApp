package com.shifthackz.benzinapp.di.module

import com.shifthackz.benzinapp.di.scope.RepositoryScope
import com.shifthackz.benzinapp.usecases.repository.AppRepository
import com.shifthackz.benzinapp.usecases.repository.database.AppDatabase
import com.shifthackz.benzinapp.usecases.repository.server.ServerCommunicator
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @RepositoryScope
    @Provides
    internal fun providesRepository(communicator: ServerCommunicator, database: AppDatabase): AppRepository {
        return AppRepository(communicator, database)
    }
}