package com.updown.superdo.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = AppDatabase.FEED_TABLE)
data class ProductInfo(
    @Expose
    @SerializedName("weight")
    val weight: String,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("bagColor")
    val bagColor: String
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}