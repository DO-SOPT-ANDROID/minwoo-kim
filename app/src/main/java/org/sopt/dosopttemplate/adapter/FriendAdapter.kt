package org.sopt.dosopttemplate.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemFriendBirthdayBinding
import org.sopt.dosopttemplate.databinding.ItemFriendInfoBinding
import org.sopt.dosopttemplate.databinding.ItemFriendMyBinding
import org.sopt.dosopttemplate.model.data.Friend

class FriendAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }

    private var friendList = mutableListOf<Friend>()

    companion object {
        private const val VIEW_TYPE_FRIEND_MY = 1
        private const val VIEW_TYPE_FRIEND_INFO = 2
        private const val VIEW_TYPE_FRIEND_BIRTHDAY = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_FRIEND_MY -> FriendMyViewHolder(
                ItemFriendMyBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )

            VIEW_TYPE_FRIEND_INFO -> FriendInfoViewHolder(
                ItemFriendInfoBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )

            VIEW_TYPE_FRIEND_BIRTHDAY -> FriendBirthdayViewHolder(
                ItemFriendBirthdayBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )

            else -> throw RuntimeException("viewType error")
        }
    }

    override fun getItemCount() = friendList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FriendMyViewHolder -> holder.onBind(friendList[position] as Friend.FriendMy)
            is FriendInfoViewHolder -> holder.onBind(friendList[position] as Friend.FriendInfo)
            is FriendBirthdayViewHolder -> holder.onBind(friendList[position] as Friend.FriendBirthday)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (friendList[position]) {
            is Friend.FriendMy -> VIEW_TYPE_FRIEND_MY
            is Friend.FriendInfo -> VIEW_TYPE_FRIEND_INFO
            is Friend.FriendBirthday -> VIEW_TYPE_FRIEND_BIRTHDAY
        }
    }

    fun setFriendList(list: MutableList<Friend>) {
        friendList.clear()
        friendList.addAll(list)
        notifyDataSetChanged()
    }
}