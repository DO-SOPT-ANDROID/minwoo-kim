package org.sopt.dosopttemplate.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.dosopttemplate.adapter.FriendAdapter
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
import org.sopt.dosopttemplate.model.data.DummyFriendsData
import org.sopt.dosopttemplate.util.base.BindingFragment

class HomeFragment : BindingFragment<FragmentHomeBinding>() {
    private lateinit var friendAdapter: FriendAdapter

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        setFriendData()
    }

    private fun initAdapter() {
        friendAdapter = FriendAdapter(requireContext())
        binding.rvHome.adapter = friendAdapter
    }

    private fun setFriendData() {
        friendAdapter.setFriendList(DummyFriendsData.friendList)
    }
}