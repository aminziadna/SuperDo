package com.updown.superdo.ui.main

import com.updown.superdo.data.ProductInfo

interface FeedFilter {
    fun filter(product: ProductInfo, key: String?): Boolean
}
