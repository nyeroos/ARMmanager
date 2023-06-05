package com.example.armmanager.ui.add

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.armmanager.vo.Product
import com.example.armmanager.databinding.ItemProductForRequestBinding

class AddRequestAdapter: RecyclerView.Adapter<AddRequestAdapter.AddRequestViewHolder>() {

    var data: List<Product> = emptyList()
        set(newValue){
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddRequestViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductForRequestBinding.inflate(inflater, parent, false)

        return AddRequestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddRequestViewHolder, position: Int) {
        val product = data[position]
        val context = holder.itemView.context

        with(holder.binding) {
            nameProductRequestRV.text = product.productName
           // amountProductRV.text = product.amount.toString()
        }
    }

    override fun getItemCount(): Int = data.size

    class AddRequestViewHolder(val binding: ItemProductForRequestBinding) : RecyclerView.ViewHolder(binding.root)
}