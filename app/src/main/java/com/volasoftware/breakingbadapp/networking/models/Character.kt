package com.volasoftware.breakingbadapp.networking.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.*

class Character(
    @SerializedName("char_id") val charId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("birthday") val birthday: String,
    @SerializedName("occupation") val occupations: ArrayList<String>?,
    @SerializedName("img") val img: String,
    @SerializedName("status") val status: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("appearance") val appearances: IntArray?,
    @SerializedName("portrayed") val portrayed: String,
    @SerializedName("category") val category: String,
    @SerializedName("better_call_saul_appearance") val betterCallSaulAppearances: IntArray?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.createStringArrayList(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.createIntArray(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.createIntArray()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(charId)
        parcel.writeString(name)
        parcel.writeString(birthday)
        parcel.writeStringList(occupations)
        parcel.writeString(img)
        parcel.writeString(status)
        parcel.writeString(nickname)
        parcel.writeIntArray(appearances)
        parcel.writeString(portrayed)
        parcel.writeString(category)
        parcel.writeIntArray(betterCallSaulAppearances)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Character> {
        override fun createFromParcel(parcel: Parcel): Character {
            return Character(parcel)
        }

        override fun newArray(size: Int): Array<Character?> {
            return arrayOfNulls(size)
        }
    }
}