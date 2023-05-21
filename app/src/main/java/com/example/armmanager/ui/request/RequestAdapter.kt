package com.example.armmanager.ui.request

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.armmanager.R
import com.example.armmanager.vo.Request

class RequestAdapter: ListAdapter<Request, RequestAdapter.RequestViewHolder>(RequestsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        val binding = ItemRequestBinding.inflate(inflater, parent, false)
        return RequestViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.number, current.name, current.status)

    }

    fun setData(newRequests: List<Request>) {
        submitList(newRequests)
        notifyDataSetChanged()
    }

    class RequestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val requestItemView: TextView = itemView.findViewById(R.id.numRequestRV) //?
        private val requestItemView2: TextView = itemView.findViewById(R.id.nameRequestRV)
        private val requestItemView3: TextView = itemView.findViewById(R.id.statusRequestRV)

        fun bind(number: Int?, name: String, status: String) {
            requestItemView.text = number.toString()
            requestItemView2.text = name
            requestItemView3.text = status
        }

        companion object {
            fun create(parent: ViewGroup): RequestViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_request, parent, false)
                return RequestViewHolder(view)
            }
        }
    }

    class RequestsComparator : DiffUtil.ItemCallback<Request>() {
        override fun areItemsTheSame(oldItem: Request, newItem: Request): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Request, newItem: Request): Boolean {
            return oldItem.id == newItem.id
        }
    }
}

