package com.example.figma_appium.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.figma_appium.databinding.ItemOfferBinding
import com.example.figma_appium.models.Offer

class OfferAdapter(
    private val offers: List<Offer>
) : RecyclerView.Adapter<OfferAdapter.OfferViewHolder>() {

    inner class OfferViewHolder(private val binding: ItemOfferBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(offer: Offer) {
            binding.apply {
                tvOfferDiscount.text = offer.title
                tvOfferDescription.text = offer.description
                tvOfferCode.text = String.format("With code:%s", offer.code)
                ivOfferImage.setImageResource(offer.imageResId)
                offerBackground.setBackgroundColor(offer.backgroundColor)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val binding = ItemOfferBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OfferViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        holder.bind(offers[position])
    }

    override fun getItemCount() = offers.size
}
