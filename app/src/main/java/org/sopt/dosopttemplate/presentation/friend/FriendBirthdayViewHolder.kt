package org.sopt.dosopttemplate.presentation.friend

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemFriendBirthdayBinding
import org.sopt.dosopttemplate.data.model.local.Friend

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