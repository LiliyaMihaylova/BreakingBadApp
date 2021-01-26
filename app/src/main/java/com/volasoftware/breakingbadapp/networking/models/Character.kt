package com.volasoftware.breakingbadapp.networking.models

import com.google.gson.annotations.SerializedName

class Character(
    @SerializedName("char_id") val charId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("birthday") val birthday: String,
    @SerializedName("occupation") val occupations: List<String>,
    @SerializedName("img") val img: String,
    @SerializedName("status") val status: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("appearance") val appearances: List<Int>,
    @SerializedName("portrayed") val portrayed: String,
    @SerializedName("category") val category: String,
    @SerializedName("better_call_saul_appearance") val betterCallSaulAppearances: List<Int>
)