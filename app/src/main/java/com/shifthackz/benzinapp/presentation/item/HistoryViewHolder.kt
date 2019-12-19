package com.shifthackz.benzinapp.presentation.item

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.shifthackz.benzinapp.usecases.repository.database.entity.HistoryEntity
import com.shifthackz.benzinapp.utils.toDateTime
import kotlinx.android.synthetic.main.item_history.view.*

class HistoryViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private var historyEntity: HistoryEntity? = null
    private var listener: ItemClickListener<HistoryEntity>? = null
    private val itemDetail = View.OnClickListener { listener!!.openDetail(this!!.historyEntity!!) }

    fun bind(history: HistoryEntity, listener: ItemClickListener<HistoryEntity>) {
        historyEntity = history
        this.listener = listener
        setupItem()
    }

    @SuppressLint("SetTextI18n")
    private fun setupItem() {
        view.txtItemHistoryBenzinName.text = historyEntity?.benzinName
        view.txtItemHistoryLiters.text = "Количество топлива: ${historyEntity?.litersCount.toString()}"
        view.txtItemHistoryDate.text = "Дата: ${historyEntity?.time?.toDateTime()}"
        view.txtItemHistoryWorker.text = "Ответственный: ${historyEntity?.workerName}"
        view.txtItemHistoryComment.text = "Комментарий: ${historyEntity?.comment}"
        view.setOnClickListener(itemDetail)
    }
}