package com.updown.superdo.ui.main

import com.updown.superdo.data.ProductInfo

class WeightFeedFilter : FeedFilter {
    override fun filter(product: ProductInfo, key: String?): Boolean {
        val weightThreshold = key?.toDoubleOrNull() ?: return true
        val weight = product.weight.replace("kg", "").toDoubleOrNull()
            ?: return false // if the product somehow doesn't have weight then don't show it
        return weight >= weightThreshold
    }
}
