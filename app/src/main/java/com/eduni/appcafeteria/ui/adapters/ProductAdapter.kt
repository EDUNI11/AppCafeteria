package com.eduni.appcafeteria.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eduni.appcafeteria.databinding.ItemProductBinding
import com.eduni.appcafeteria.model.Product

class ProductAdapter(private val onClick: (Product) -> Unit) :
    ListAdapter<Product, ProductAdapter.ProductViewHolder>(ProductDiffCallback()) {

    private val selectedProducts = mutableMapOf<String, Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)
    }

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.tvItemName.text = product.name
            binding.tvItemPrice.text = "${product.price} €"

            val isSelected = selectedProducts[product.category] == product
            binding.root.setBackgroundColor(
                if (isSelected) itemView.context.getColor(android.R.color.darker_gray)
                else itemView.context.getColor(android.R.color.transparent)
            )

            binding.root.setOnClickListener {
                if (isSelected) {
                    selectedProducts.remove(product.category)
                } else {
                    selectedProducts[product.category] = product
                }
                onClick(product)
                notifyDataSetChanged()
            }
        }
    }
}
