package org.sopt.dosopttemplate.adapter

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemFriendBirthdayBinding
import org.sopt.dosopttemplate.repository.model.data.Friend

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