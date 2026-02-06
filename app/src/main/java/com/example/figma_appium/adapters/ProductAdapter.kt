package com.example.figma_appium.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.figma_appium.databinding.ItemProductBinding
import com.example.figma_appium.models.Product

class ProductAdapter(
    private val products: List<Product>,
    private val onProductClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.apply {
                tvProductBrand.text = product.brand
                tvProductName.text = product.name
                tvProductPrice.text = String.format("$%.2f", product.price)
                ivProductImage.setImageResource(product.imageResId)
                
                root.setOnClickListener {
                    onProductClick(product)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount() = products.size
}
