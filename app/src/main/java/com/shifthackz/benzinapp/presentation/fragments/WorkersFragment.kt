package com.shifthackz.benzinapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.shifthackz.benzinapp.R
import com.shifthackz.benzinapp.di.component.ViewModelComponent
import com.shifthackz.benzinapp.domain.AllWorkersViewModel
import com.shifthackz.benzinapp.presentation.adapter.WorkersAdapter
import com.shifthackz.benzinapp.presentation.base.BaseFragment
import com.shifthackz.benzinapp.presentation.item.ItemClickListener
import com.shifthackz.benzinapp.usecases.repository.database.entity.WorkerEntity
import kotlinx.android.synthetic.main.fragment_workers.*
import javax.inject.Inject

class WorkersFragment : BaseFragment() {

    var viewModel: AllWorkersViewModel? = null
        @Inject set

    private lateinit var workersAdapter: WorkersAdapter

    private val itemClickListener = object : ItemClickListener<WorkerEntity> {
        override fun openDetail(m: WorkerEntity) {
            openItemDetail(m.id)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_workers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
    }

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    override fun getLayout(): Int = R.layout.fragment_workers

    private fun getData() {
        viewModel?.getAllItems()
        viewModel?.getLiveDataItems()?.apply {
            if (!hasActiveObservers()) {
                observe(this@WorkersFragment, Observer {
                    it?.let { initRecyclerView(it) }
                })
            }
        }
    }

    private fun initRecyclerView(workers: List<WorkerEntity>) {
        workersAdapter = WorkersAdapter(requireContext(), workers, itemClickListener)
        workersAdapter.setItemClickListener(itemClickListener)
        rvWorkers.layoutManager = LinearLayoutManager(requireContext())
        rvWorkers.adapter = workersAdapter
    }

    private fun openItemDetail(id: Int) {
        //this.startActivity(DetailActivity.newInstance(this@, id))
    }

    companion object {
        @JvmStatic
        fun newInstance() = WorkersFragment()
    }
}
