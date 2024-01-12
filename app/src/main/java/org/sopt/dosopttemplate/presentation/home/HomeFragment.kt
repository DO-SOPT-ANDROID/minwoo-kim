package org.sopt.dosopttemplate.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
import org.sopt.dosopttemplate.util.UiState
import org.sopt.dosopttemplate.util.base.BindingFragment
import org.sopt.dosopttemplate.util.context.shortSnackBar

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>() {
    private lateinit var followerAdapter: FollowerAdapter
    private val homeViewModel by viewModels<HomeViewModel>()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        getFollower()
        collectFollower()
    }

    private fun initAdapter() {
        followerAdapter = FollowerAdapter(requireContext())
        binding.rvHome.adapter = followerAdapter
    }

    private fun getFollower() {
        homeViewModel.getFollowerList(page)
    }

    private fun collectFollower() {
        homeViewModel.uiState.flowWithLifecycle(lifecycle).onEach { state ->
            when (state) {
                is UiState.Success -> {
                    followerAdapter.setFollowerList(state.data)
                    shortSnackBar(binding.root, "데이터 불러오기 성공")
                }

                is UiState.Failure -> {
                    shortSnackBar(binding.root, "데이터 불러오기 실패, ${state.msg}")
                }

                is UiState.Loading -> {
                    shortSnackBar(binding.root, "데이터 불러오는 중")
                }

                is UiState.Empty -> {
                    shortSnackBar(binding.root, "데이터가 비어있습니다.")
                    return@onEach
                }
            }
        }.launchIn(lifecycleScope)
    }

    companion object {
        const val page = 2
    }
}