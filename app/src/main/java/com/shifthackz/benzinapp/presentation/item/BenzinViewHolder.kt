package com.shifthackz.benzinapp.presentation.item

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.shifthackz.benzinapp.usecases.repository.database.entity.BenzinEntity
import kotlinx.android.synthetic.main.item_benzin.view.*

class BenzinViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private var benzinEntity: BenzinEntity? = null
    private var listener: ItemClickListener<BenzinEntity>? = null
    private val itemDetail = View.OnClickListener { listener!!.openDetail(this!!.benzinEntity!!) }

    fun bind(benzin: BenzinEntity, listener: ItemClickListener<BenzinEntity>) {
        benzinEntity = benzin
        this.listener = listener
        setupItem()
    }

    @SuppressLint("SetTextI18n")
    private fun setupItem() {
        view.txtItemBenzinName.text = benzinEntity?.name
        view.txtItemBenzinZapas.text = "Запас топлива: " + benzinEntity?.zapas.toString() + " л"
        view.txtItemBenzinPriceSell.text = "Цена продажи: ${benzinEntity?.priceSell} грн"
        view.txtItemBenzinPriceBuy.text = "Цена закупки: ${benzinEntity?.priceBuy} грн"
        view.setOnClickListener(itemDetail)
    }
}