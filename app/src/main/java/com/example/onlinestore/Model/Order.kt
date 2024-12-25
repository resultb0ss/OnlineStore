package com.example.onlinestore.Model

import android.os.Parcel
import android.os.Parcelable

data class Order(
    val id: Int = 1,
    val customId: String = "",
    val timestamp: String = "",
    val status: String = "",
    val coast: String = ""
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {

    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(customId)
        dest.writeString(timestamp)
        dest.writeString(status)
        dest.writeString(coast)

    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(source: Parcel): User {
            return User(source)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }

    }

}