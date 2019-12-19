package com.shifthackz.benzinapp.presentation.activities.add

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.shifthackz.benzinapp.R
import com.shifthackz.benzinapp.di.component.ViewModelComponent
import com.shifthackz.benzinapp.domain.SingleHistoryViewModel
import com.shifthackz.benzinapp.presentation.base.BaseActivity
import com.shifthackz.benzinapp.usecases.repository.database.entity.HistoryEntity
import kotlinx.android.synthetic.main.activity_add_order.*
import javax.inject.Inject

class AddOrderActivity : BaseActivity() {

    var viewModel: SingleHistoryViewModel? = null
        @Inject set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_order)
        initAppBar()
        spinnerBenzin()
        spinnerWorker()
        spinnerType()
        setupButton()
    }

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun spinnerBenzin() {
        val adapter = ArrayAdapter.createFromResource(this@AddOrderActivity, R.array.benzins, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerBenzin.adapter = adapter
    }

    private fun spinnerWorker() {
        val adapter = ArrayAdapter.createFromResource(this@AddOrderActivity, R.array.workers, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerWorker.adapter = adapter
    }

    private fun spinnerType() {
        val adapter = ArrayAdapter.createFromResource(this@AddOrderActivity, R.array.type, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerType.adapter = adapter
    }

    private fun setupButton() {
        btnAddR.setOnClickListener {
            viewModel?.addItem(HistoryEntity(99,
                System.currentTimeMillis(),
                spinnerBenzin.selectedItem.toString(),
                if (spinnerType.selectedItem.toString() == "Продажа")
                    (-1 * etEditAddLiters.text.toString().toInt())
                else etEditAddLiters.text.toString().toInt(),
                0,
                spinnerWorker.selectedItem.toString(),
                etEditAddComment.text.toString()))
            finish()
        }
    }

    private fun initAppBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Добавить новый заказ"
    }

    companion object {
        @JvmStatic
        fun newInstance(context: Context) = Intent(context, AddOrderActivity::class.java)
    }
}
