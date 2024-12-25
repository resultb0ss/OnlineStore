package com.example.onlinestore.Model

import android.os.Parcel
import android.os.Parcelable

data class User(

    val id: Int = 1,
    var firstName: String = "",
    var lastName: String = "",
    var phone: String = "",
    var email: String = "",

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
        dest.writeString(firstName)
        dest.writeString(lastName)
        dest.writeString(phone)
        dest.writeString(email)

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
