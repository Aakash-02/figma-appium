package com.example.figma_appium

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.figma_appium.databinding.ItemProductBinding

class ProductAdapter(
    private val products: List<Product>,
    private val onProductClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.binding.apply {
            tvProductName.text = product.name
            tvProductSubtitle.text = product.subtitle
            tvProductPrice.text = product.price
            ivProduct.setImageResource(product.imageResId)

            cardProduct.setOnClickListener {
                onProductClick(product)
            }

            ivLove.setOnClickListener {
                product.isFavorite = !product.isFavorite
                // Update the icon based on favorite status
            }
        }
    }

    override fun getItemCount() = products.size
}
