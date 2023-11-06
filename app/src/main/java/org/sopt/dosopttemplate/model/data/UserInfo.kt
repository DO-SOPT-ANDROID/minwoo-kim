package org.sopt.dosopttemplate.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfo(
    val id: String,
    val pw: String,
    val nickName: String,
    val address: String
) : Parcelable