package com.shifthackz.benzinapp.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.shifthackz.benzinapp.R
import com.shifthackz.benzinapp.presentation.base.BaseAdapter
import com.shifthackz.benzinapp.presentation.item.BenzinViewHolder
import com.shifthackz.benzinapp.presentation.item.ItemClickListener
import com.shifthackz.benzinapp.usecases.repository.database.entity.BenzinEntity

class BenzinsAdapter(private val context: Context,
                     private val benzins: List<BenzinEntity>,
                     private val listener: ItemClickListener<BenzinEntity>
) : BaseAdapter<BenzinViewHolder, BenzinEntity, ItemClickListener<BenzinEntity>>
        (benzins as MutableList<BenzinEntity>, listener) {

    override fun getItemCount() = benzins.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BenzinViewHolder {
        return BenzinViewHolder(LayoutInflater.from(context).inflate(R.layout.item_benzin, parent, false))
    }

    override fun onBindViewHolder(benzinViewHolder: BenzinViewHolder, i: Int) {
        super.onBindViewHolder(benzinViewHolder, i)
        benzinViewHolder.bind(benzins[i], listener)
    }
}