package com.shifthackz.benzinapp.presentation.item

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.shifthackz.benzinapp.usecases.repository.database.entity.WorkerEntity
import com.shifthackz.benzinapp.utils.toDate
import kotlinx.android.synthetic.main.item_worker.view.*

class WorkerViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private var workerEntity: WorkerEntity? = null
    private var listener: ItemClickListener<WorkerEntity>? = null
    private val itemDetail = View.OnClickListener { listener!!.openDetail(this!!.workerEntity!!) }

    fun bind(worker: WorkerEntity, listener: ItemClickListener<WorkerEntity>) {
        workerEntity = worker
        this.listener = listener
        setupItem()
    }

    @SuppressLint("SetTextI18n")
    private fun setupItem() {
        view.txtItemWorkerName.text = "${workerEntity?.lastName} ${workerEntity?.firstName} ${workerEntity?.fatherName}"
        view.txtItemWorkerPosition.text = "${workerEntity?.position}, ${workerEntity?.phone}, ${workerEntity?.birthday?.toDate()}"
        view.setOnClickListener(itemDetail)
    }
}
