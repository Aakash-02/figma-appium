package com.example.figma_appium.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.figma_appium.databinding.ItemCartBinding
import com.example.figma_appium.models.CartItem

class CartAdapter(
    private val cartItems: MutableList<CartItem>,
    private val onQuantityChanged: () -> Unit,
    private val onItemDeleted: (CartItem) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(private val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cartItem: CartItem) {
            binding.apply {
                tvCartItemBrand.text = cartItem.product.brand
                tvCartItemName.text = cartItem.product.name
                tvCartItemPrice.text = String.format("$%.2f", cartItem.product.price)
                tvCartItemQuantity.text = cartItem.quantity.toString()
                ivCartItemImage.setImageResource(cartItem.product.imageResId)
                
                btnIncreaseCart.setOnClickListener {
                    cartItem.quantity++
                    notifyItemChanged(adapterPosition)
                    onQuantityChanged()
                }
                
                btnDecreaseCart.setOnClickListener {
                    if (cartItem.quantity > 1) {
                        cartItem.quantity--
                        notifyItemChanged(adapterPosition)
                        onQuantityChanged()
                    }
                }
                
                btnDelete.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        onItemDeleted(cartItem)
                        cartItems.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position, cartItems.size)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(cartItems[position])
    }

    override fun getItemCount() = cartItems.size
}
