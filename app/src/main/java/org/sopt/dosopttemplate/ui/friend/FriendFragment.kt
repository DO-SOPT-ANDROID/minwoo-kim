package org.sopt.dosopttemplate.ui.friend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.FragmentFriendBinding
import org.sopt.dosopttemplate.model.data.DummyFriendsData
import org.sopt.dosopttemplate.util.base.BindingFragment
import org.sopt.dosopttemplate.util.context.shortSnackBar

class FriendFragment : BindingFragment<FragmentFriendBinding>() {
    private lateinit var friendAdapter: FriendAdapter

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFriendBinding = FragmentFriendBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initMenuClickListener()
        setFriendData()
    }

    private fun initMenuClickListener() {
        binding.tbFriend.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.item_search -> {
                    shortSnackBar(binding.root, "검색 아이콘이 눌렀습니다.")
                    true
                }

                R.id.item_setting -> {
                    shortSnackBar(binding.root, "설정 아이콘이 눌렀습니다.")
                    true
                }

                else -> false
            }
        }
    }

    private fun initAdapter() {
        friendAdapter = FriendAdapter(requireContext())
        binding.rvFriend.adapter = friendAdapter
    }

    private fun setFriendData() {
        friendAdapter.setFriendList(DummyFriendsData.friendList)
    }
}