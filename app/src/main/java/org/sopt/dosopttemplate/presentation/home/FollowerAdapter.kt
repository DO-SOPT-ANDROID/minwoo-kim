package org.sopt.dosopttemplate.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemFollowerBinding
import org.sopt.dosopttemplate.domain.model.Follower

class FollowerAdapter(context: Context) :
    RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    private var followerList = listOf<Follower>()

    inner class FollowerViewHolder(private val binding: ItemFollowerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(followerData: Follower) {
            binding.followerData = followerData
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        return FollowerViewHolder(ItemFollowerBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount() = followerList.size

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(followerList[position])
    }

    fun setFollowerList(follower: List<Follower>) {
        followerList = follower
        notifyDataSetChanged()
    }
}