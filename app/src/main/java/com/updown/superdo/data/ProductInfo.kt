package com.updown.superdo.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProductInfo(
    @Expose
    @SerializedName("weight")
    val weight:String,
    @Expose
    @SerializedName("name")
    val name:String,
    @Expose
    @SerializedName("bagColor")
    val bagColor:String)