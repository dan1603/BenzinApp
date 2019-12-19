package com.shifthackz.benzinapp.di.component

import com.shifthackz.benzinapp.di.module.ApiModule
import com.shifthackz.benzinapp.di.scope.ApiScope
import com.shifthackz.benzinapp.usecases.repository.server.ServerCommunicator
import dagger.Component

@ApiScope
@Component(modules = [ApiModule::class])
interface ApiComponent {
    val serverCommunicator: ServerCommunicator
}
