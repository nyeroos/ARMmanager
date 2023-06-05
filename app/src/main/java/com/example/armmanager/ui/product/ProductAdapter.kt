package com.example.armmanager.ui.product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.armmanager.R
import com.example.armmanager.vo.Product
import com.example.armmanager.vo.Request

class ProductAdapter: ListAdapter<Product, ProductAdapter.ProductViewHolder>(ProductsComparator()) {

    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder.create(parent, listener)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)

    }

    interface OnItemClickListener {
        fun onItemClick(product: Product)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    fun removeItem(position: Int) {
        val currentList = currentList.toMutableList()
        currentList.removeAt(position)
        submitList(currentList)
    }

    class ProductViewHolder(itemView: View, private val listener: OnItemClickListener?) :
        RecyclerView.ViewHolder(itemView) {
        private val productItemView: TextView = itemView.findViewById(R.id.nameProductRV) //?

        fun bind(product: Product) {
            productItemView.text = product.productName

            itemView.setOnClickListener {
                listener?.onItemClick(product)
            }

        }

        companion object {
            fun create(parent: ViewGroup, listener: OnItemClickListener?): ProductViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_product, parent, false)
                return ProductViewHolder(view, listener)
            }
        }
    }

    class ProductsComparator : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }
    }
}