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

    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        return RequestViewHolder.create(parent, listener)
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)

    }

    fun setData(newRequests: List<Request>) {
        submitList(newRequests)
        //notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(request: Request)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    fun removeItem(position: Int) {
        val currentList = currentList.toMutableList()
        currentList.removeAt(position)
        submitList(currentList)
    }

    class RequestViewHolder(itemView: View, private val listener: OnItemClickListener?) : RecyclerView.ViewHolder(itemView) {
        private val requestItemView: TextView = itemView.findViewById(R.id.numRequestRV) //?
        private val requestItemView2: TextView = itemView.findViewById(R.id.nameRequestRV)
        private val requestItemView3: TextView = itemView.findViewById(R.id.statusRequestRV)

        fun bind(request: Request) {
            requestItemView.text = request.number.toString()
            requestItemView2.text = request.name
            requestItemView3.text = request.status

            itemView.setOnClickListener {
                listener?.onItemClick(request)
            }

            if (request.status =="TTTT"){
               // itemView.setBackgroundColor()
            }
        }

        companion object {
            fun create(parent: ViewGroup, listener: OnItemClickListener?): RequestViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_request, parent, false)
                return RequestViewHolder(view, listener)
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