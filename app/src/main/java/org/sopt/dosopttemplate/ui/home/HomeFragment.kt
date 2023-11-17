package org.sopt.dosopttemplate.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.dosopttemplate.data.remote.api.ServicePool
import org.sopt.dosopttemplate.data.remote.model.dto.response.follower.FollowerRes
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
import org.sopt.dosopttemplate.util.base.BindingFragment
import org.sopt.dosopttemplate.util.context.shortToast
import retrofit2.Call
import retrofit2.Response

class HomeFragment : BindingFragment<FragmentHomeBinding>() {
    private lateinit var followerAdapter: FollowerAdapter
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        ServicePool.followerService.getFollowerList(2)
            .enqueue(object : retrofit2.Callback<FollowerRes> {
                override fun onResponse(
                    call: Call<FollowerRes>,
                    response: Response<FollowerRes>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()?.data

                        setFollowerList(data ?: return)
                    }
                }

                override fun onFailure(call: Call<FollowerRes>, t: Throwable) {
                    requireContext().shortToast("서버 에러 발생")
                }
            })
    }

    private fun initAdapter() {
        followerAdapter = FollowerAdapter(requireContext())
        binding.rvHome.adapter = followerAdapter
    }

    fun setFollowerList(followerData: List<FollowerRes.FollowerData>) {
        followerAdapter.setFollowerList(followerData)
    }
}