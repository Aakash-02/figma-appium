package com.example.figma_appium

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.figma_appium.databinding.ItemCartBinding

class CartAdapter(
    private val cartItems: MutableList<CartItem>,
    private val onQuantityChanged: () -> Unit,
    private val onItemDeleted: (Int) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = cartItems[position]
        holder.binding.apply {
            tvProductName.text = cartItem.product.name
            tvProductSubtitle.text = cartItem.product.subtitle
            tvProductPrice.text = cartItem.product.price
            tvQuantity.text = cartItem.quantity.toString()
            ivProduct.setImageResource(cartItem.product.imageResId)

            tvMinus.setOnClickListener {
                if (cartItem.quantity > 1) {
                    cartItem.quantity--
                    tvQuantity.text = cartItem.quantity.toString()
                    onQuantityChanged()
                }
            }

            tvPlus.setOnClickListener {
                cartItem.quantity++
                tvQuantity.text = cartItem.quantity.toString()
                onQuantityChanged()
            }

            ivDelete.setOnClickListener {
                onItemDeleted(position)
            }
        }
    }

    override fun getItemCount() = cartItems.size

    fun removeItem(position: Int) {
        cartItems.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, cartItems.size)
    }
}
