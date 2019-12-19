package com.shifthackz.benzinapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.shifthackz.benzinapp.R
import com.shifthackz.benzinapp.di.component.ViewModelComponent
import com.shifthackz.benzinapp.domain.AllHistoryViewModel
import com.shifthackz.benzinapp.presentation.activities.add.AddOrderActivity
import com.shifthackz.benzinapp.presentation.adapter.HistoryAdapter
import com.shifthackz.benzinapp.presentation.base.BaseFragment
import com.shifthackz.benzinapp.presentation.item.ItemClickListener
import com.shifthackz.benzinapp.usecases.repository.database.entity.HistoryEntity
import kotlinx.android.synthetic.main.fragment_order.*
import javax.inject.Inject

class OrderFragment : BaseFragment() {

    private val itemClickListener = object : ItemClickListener<HistoryEntity> {
        override fun openDetail(m: HistoryEntity) {
            openItemDetail(m.id)
        }
    }

    private lateinit var historyAdapter: HistoryAdapter

    var allHistoryViewModel: AllHistoryViewModel? = null
        @Inject set


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAddButton()
        getData()
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    override fun getLayout() = R.layout.fragment_order

    private fun getData() {
        allHistoryViewModel?.getAllItems()
        allHistoryViewModel?.getLiveDataItems()?.apply {
            if (!hasActiveObservers()) {
                observe(this@OrderFragment, Observer {
                    it?.let {
                        initRecyclerView(it)
                    }
                })
            }
        }
    }

    private fun initRecyclerView(list: List<HistoryEntity>) {
        historyAdapter = HistoryAdapter(requireContext(), list, itemClickListener)
        historyAdapter.setItemClickListener(itemClickListener)
        rvHistory.layoutManager = LinearLayoutManager(requireContext())
        rvHistory.adapter = historyAdapter
    }

    private fun openItemDetail(id: Int) {
        //this.startActivity(DetailActivity.newInstance(this@, id))
    }

    private fun initAddButton() {
        fabOrderAdd.setOnClickListener {
            startActivity(AddOrderActivity.newInstance(requireContext()))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = OrderFragment()
    }
}
