package org.sopt.dosopttemplate.adapter

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemFriendMyBinding
import org.sopt.dosopttemplate.repository.model.data.Friend

class FriendMyViewHolder(private val binding: ItemFriendMyBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(friendMyData: Friend.FriendMy) {
        binding.run {
            ivFriendMyProfile.setImageResource(friendMyData.profileImage)
            tvFriendMyName.text = friendMyData.name
        }
    }
}