package com.shifthackz.benzinapp.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.shifthackz.benzinapp.R
import com.shifthackz.benzinapp.presentation.base.BaseAdapter
import com.shifthackz.benzinapp.presentation.item.HistoryViewHolder
import com.shifthackz.benzinapp.presentation.item.ItemClickListener
import com.shifthackz.benzinapp.usecases.repository.database.entity.HistoryEntity

class HistoryAdapter(
    private val context: Context,
    private val history: List<HistoryEntity>,
    private val listener: ItemClickListener<HistoryEntity>
) : BaseAdapter<HistoryViewHolder, HistoryEntity, ItemClickListener<HistoryEntity>>
    (history as MutableList<HistoryEntity>, listener) {

    override fun getItemCount() = history.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(LayoutInflater.from(context).inflate(R.layout.item_history, parent, false))
    }

    override fun onBindViewHolder(historyViewHolder: HistoryViewHolder, i: Int) {
        super.onBindViewHolder(historyViewHolder, i)
        historyViewHolder.bind(history[i], listener)
    }
}