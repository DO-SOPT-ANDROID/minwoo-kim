package org.sopt.dosopttemplate.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import org.sopt.dosopttemplate.data.local.HomeState
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
import org.sopt.dosopttemplate.ui.common.ViewModelFactory
import org.sopt.dosopttemplate.util.base.BindingFragment
import org.sopt.dosopttemplate.util.context.shortSnackBar

class HomeFragment : BindingFragment<FragmentHomeBinding>() {
    private lateinit var followerAdapter: FollowerAdapter
    private val viewModel: HomeViewModel by viewModels { ViewModelFactory() }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        getFollowerList()
        observeHomeState()
    }

    private fun getFollowerList() {
        viewModel.getFollowerList()
    }

    private fun initAdapter() {
        followerAdapter = FollowerAdapter(requireContext())
        binding.rvHome.adapter = followerAdapter
    }

    private fun setFollowerList() {
        viewModel.followerList.observe(viewLifecycleOwner) { followerList ->
            followerAdapter.setFollowerList(followerList)
        }
    }

    private fun observeHomeState() {
        viewModel.homeState.observe(viewLifecycleOwner) { homeState ->
            when (homeState) {
                is HomeState.Success -> {
                    setFollowerList()
                    shortSnackBar(binding.root, "데이터 불러오기 성공")
                }

                is HomeState.Error -> {
                    shortSnackBar(binding.root, "데이터 불러오기 실패")
                }

                is HomeState.Loading -> {
                    shortSnackBar(binding.root, "데이터 불러오는 중")
                }
            }
        }
    }
}