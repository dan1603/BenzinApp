package com.shifthackz.benzinapp.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.shifthackz.benzinapp.R
import com.shifthackz.benzinapp.presentation.base.BaseAdapter
import com.shifthackz.benzinapp.presentation.item.ItemClickListener
import com.shifthackz.benzinapp.presentation.item.WorkerViewHolder
import com.shifthackz.benzinapp.usecases.repository.database.entity.WorkerEntity

class WorkersAdapter(private val context: Context,
                  private val workers: List<WorkerEntity>,
                  private val listener: ItemClickListener<WorkerEntity>
) : BaseAdapter<WorkerViewHolder, WorkerEntity, ItemClickListener<WorkerEntity>>
        (workers as MutableList<WorkerEntity>, listener) {

    override fun getItemCount() = workers.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkerViewHolder {
        return WorkerViewHolder(LayoutInflater.from(context).inflate(R.layout.item_worker, parent, false))
    }

    override fun onBindViewHolder(workerViewHolder: WorkerViewHolder, i: Int) {
        super.onBindViewHolder(workerViewHolder, i)
        workerViewHolder.bind(workers[i], listener)
    }
}
