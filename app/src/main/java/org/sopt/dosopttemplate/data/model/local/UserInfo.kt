package org.sopt.dosopttemplate.data.model.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfo(
    val id: String?,
    val pw: String?,
    val nickName: String?,
    val address: String?
) : Parcelable