package org.sopt.dosopttemplate.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.remote.model.dto.response.follower.FollowerRes
import org.sopt.dosopttemplate.databinding.ItemFollowerBinding

class FollowerAdapter(context: Context) :
    RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    private var followerList = mutableListOf<FollowerRes.FollowerData>()

    inner class FollowerViewHolder(private val binding: ItemFollowerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(followerData: FollowerRes.FollowerData) {
            binding.run {
                tvFollowerName.text = followerData.first_name
                tvFollowerEmail.text = followerData.email
                ivFollower.load(followerData.avatar) {
                    placeholder(R.drawable.img_jjang_gu)
                    error(R.drawable.img_jjang_gu)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        return FollowerViewHolder(ItemFollowerBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount() = followerList.size

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(followerList[position])
    }

    fun setFollowerList(followerData: List<FollowerRes.FollowerData>) {
        followerList.clear()
        followerList.addAll(followerData)
        notifyDataSetChanged()
    }
}