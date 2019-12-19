package com.shifthackz.benzinapp.di.component

import com.shifthackz.benzinapp.di.module.ViewModelModule
import com.shifthackz.benzinapp.di.scope.ViewModelScope
import com.shifthackz.benzinapp.presentation.activities.add.AddOrderActivity
import com.shifthackz.benzinapp.presentation.activities.detail.DetailActivity
import com.shifthackz.benzinapp.presentation.activities.main.MainActivity
import com.shifthackz.benzinapp.presentation.fragments.BenzinFragment
import com.shifthackz.benzinapp.presentation.fragments.OrderFragment
import com.shifthackz.benzinapp.presentation.fragments.WorkersFragment
import dagger.Component

@ViewModelScope
@Component(modules = [ViewModelModule::class], dependencies = [InteractorComponent::class])
interface ViewModelComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: DetailActivity)
    fun inject(fragment: WorkersFragment)
    fun inject(fragment: BenzinFragment)
    fun inject(fragment: OrderFragment)
    fun inject(activity: AddOrderActivity)
}