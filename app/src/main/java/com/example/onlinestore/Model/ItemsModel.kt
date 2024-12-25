package com.example.onlinestore.Model


import android.os.Parcel
import android.os.Parcelable

data class ItemsModel(
    var title: String = "",
    var description: String = "",
    var picUrl: ArrayList<String> = ArrayList(),
    var model: ArrayList<String> = ArrayList(),
    var price: Double = 0.0,
    var rating: Double = 0.0,
    var numberInCart: Int = 0,
    var showRecommended: Boolean = false,
    var categoryId: Int = 0
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.createStringArrayList() as ArrayList<String>,
        parcel.createStringArrayList() as ArrayList<String>,
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readInt()
    ) {

    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(title)
        dest.writeString(description)
        dest.writeStringList(picUrl)
        dest.writeStringList(model)
        dest.writeDouble(price)
        dest.writeDouble(rating)
        dest.writeInt(numberInCart)
        dest.writeByte(if (showRecommended) 1 else 0)
        dest.writeInt(categoryId)
    }

    companion object CREATOR : Parcelable.Creator<ItemsModel> {
        override fun createFromParcel(source: Parcel): ItemsModel {
            return ItemsModel(source)
        }

        override fun newArray(size: Int): Array<out ItemsModel?> {
            return arrayOfNulls(size)
        }

    }

}