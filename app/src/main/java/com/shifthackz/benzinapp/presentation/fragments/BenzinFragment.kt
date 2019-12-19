package com.shifthackz.benzinapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.shifthackz.benzinapp.R
import com.shifthackz.benzinapp.di.component.ViewModelComponent
import com.shifthackz.benzinapp.domain.AllBenzinsViewModel
import com.shifthackz.benzinapp.presentation.activities.detail.DetailActivity
import com.shifthackz.benzinapp.presentation.adapter.BenzinsAdapter
import com.shifthackz.benzinapp.presentation.base.BaseFragment
import com.shifthackz.benzinapp.presentation.item.ItemClickListener
import com.shifthackz.benzinapp.usecases.repository.database.entity.BenzinEntity
import kotlinx.android.synthetic.main.fragment_benzin.*
import javax.inject.Inject

class BenzinFragment : BaseFragment() {

    private val itemClickListener = object : ItemClickListener<BenzinEntity> {
        override fun openDetail(m: BenzinEntity) {
            openItemDetail(m.id)
        }
    }

    private lateinit var benzinsAdapter: BenzinsAdapter

    var viewModel: AllBenzinsViewModel? = null
        @Inject set

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_benzin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
    }

    override fun getLayout() = R.layout.fragment_benzin

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    private fun getData() {
        viewModel?.getAllItems()
        viewModel?.getLiveDataItems()?.apply {
            if (!hasActiveObservers()) {
                observe(this@BenzinFragment, Observer {
                    it?.let { initRecyclerView(it) }
                })
            }
        }
    }

    private fun initRecyclerView(list: List<BenzinEntity>) {
        benzinsAdapter = BenzinsAdapter(requireContext(), list, itemClickListener)
        benzinsAdapter.setItemClickListener(itemClickListener)
        rvBenzins.layoutManager = LinearLayoutManager(requireContext())
        rvBenzins.adapter = benzinsAdapter
    }

    private fun openItemDetail(id: Int) {
        this.startActivity(DetailActivity.newInstance(requireContext(), id))
    }

    companion object {
        @JvmStatic
        fun newInstance() = BenzinFragment()
    }
}
