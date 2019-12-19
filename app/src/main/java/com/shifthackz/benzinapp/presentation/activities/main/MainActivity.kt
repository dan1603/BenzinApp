package com.shifthackz.benzinapp.presentation.activities.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.shifthackz.benzinapp.R
import com.shifthackz.benzinapp.di.component.ViewModelComponent
import com.shifthackz.benzinapp.domain.AllWorkersViewModel
import com.shifthackz.benzinapp.presentation.activities.detail.DetailActivity
import com.shifthackz.benzinapp.presentation.base.BaseActivity
import com.shifthackz.benzinapp.presentation.fragments.BenzinFragment
import com.shifthackz.benzinapp.presentation.fragments.OrderFragment
import com.shifthackz.benzinapp.presentation.fragments.WorkersFragment
import kotlinx.android.synthetic.main.inc_bottom_nav.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    var viewModel: AllWorkersViewModel? = null
        @Inject set

    private var onTabSelectedListener = AHBottomNavigation.OnTabSelectedListener { position: Int, wasSelected: Boolean ->
        if (wasSelected) return@OnTabSelectedListener false

        when (position) {
            0 -> {
                supportFragmentManager.beginTransaction().replace(R.id.mainContainer, WorkersFragment.newInstance()).commit()
                supportActionBar?.title = "Работники"
            }
            1 -> {
                supportFragmentManager.beginTransaction().replace(R.id.mainContainer, BenzinFragment.newInstance()).commit()
                supportActionBar?.title = "Бензин"
            }
            2 -> {
                supportFragmentManager.beginTransaction().replace(R.id.mainContainer, OrderFragment.newInstance()).commit()
                supportActionBar?.title = "Заказы"
            }
        }

        return@OnTabSelectedListener true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBottomNavigation()
    }

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    private fun initBottomNavigation() {
        bottomNavigation.setOnTabSelectedListener(onTabSelectedListener)

        val item1 = AHBottomNavigationItem("Работники", R.drawable.ic_people_alt_24px, R.color.colorPrimary)
        val item2 = AHBottomNavigationItem("Бензин", R.drawable.ic_local_drink_24px, R.color.colorPrimary)
        val item3 = AHBottomNavigationItem("Заказы", R.drawable.ic_format_list_numbered_24px, R.color.colorPrimary)

        bottomNavigation.addItem(item1)
        bottomNavigation.addItem(item2)
        bottomNavigation.addItem(item3)

        onTabSelectedListener.onTabSelected(0, false)
    }

    private fun openItemDetail(id: Int) {
        this.startActivity(DetailActivity.newInstance(this, id))
    }

    companion object {
        @JvmStatic
        fun newInstance(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            return intent
        }
    }
}
