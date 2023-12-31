package org.sopt.dosopttemplate.ui.friend

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemFriendBirthdayBinding
import org.sopt.dosopttemplate.data.local.Friend

class FriendBirthdayViewHolder(private val binding: ItemFriendBirthdayBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(friendBirthdayData: Friend.FriendBirthday) {
        binding.run {
            ivFriendBirthdayProfile.setImageResource(friendBirthdayData.profileImage)
            tvFriendBirthdayName.text = friendBirthdayData.name
            tvFriendBirthdayDescription.text = friendBirthdayData.description
        }
    }
}