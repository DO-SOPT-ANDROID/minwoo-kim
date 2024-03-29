package org.sopt.dosopttemplate.presentation.friend

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemFriendInfoBinding
import org.sopt.dosopttemplate.data.model.local.Friend

class FriendInfoViewHolder(private val binding: ItemFriendInfoBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(friendInfoData: Friend.FriendInfo) {
        binding.run {
            ivFriendInfoProfile.setImageResource(friendInfoData.profileImage)
            tvFriendInfoName.text = friendInfoData.name
            tvFriendInfoDescription.text = friendInfoData.description
            tvFriendMusicTitle.text = friendInfoData.music
        }
    }
}