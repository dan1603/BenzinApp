package com.shifthackz.benzinapp.presentation.activities.detail

import androidx.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.shifthackz.benzinapp.R
import com.shifthackz.benzinapp.di.component.ViewModelComponent
import com.shifthackz.benzinapp.domain.SingleBenzinViewModel
import com.shifthackz.benzinapp.presentation.base.BaseActivity
import com.shifthackz.benzinapp.usecases.repository.database.entity.BenzinEntity
import kotlinx.android.synthetic.main.activity_detail.*
import java.util.*
import javax.inject.Inject

class DetailActivity : BaseActivity() {

    var viewModel: SingleBenzinViewModel? = null
        @Inject set

    private var benzinId: Int = 0

    companion object {
        @JvmStatic
        fun newInstance(context: Context, id: Int): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(context.getString(R.string.EXTRAS_ID), id)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)
        initViewModel()
    }

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    private fun initViewModel() {
        benzinId = intent.getIntExtra(getString(R.string.EXTRAS_ID), 0)
        println(benzinId.toString())
        viewModel?.getItem(benzinId)
        viewModel?.getLiveDataItem()?.observe(this, Observer { it?.let { initTextViews(it) } })
    }

    private fun initTextViews(benzin: BenzinEntity) {
        txtDetailName.text = benzin.name
        txtDetailZakup.text = benzin.priceBuy.toString() + " грн"
        txtDetailSell.text = benzin.priceSell.toString() + " грн"
        txtDetailZapas.text = benzin.zapas.toString() + " л"
        initActionBar("Статистика для " + benzin.name)
    }

    private fun initActionBar(title: String) {
        Objects.requireNonNull(supportActionBar)?.title = title
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
