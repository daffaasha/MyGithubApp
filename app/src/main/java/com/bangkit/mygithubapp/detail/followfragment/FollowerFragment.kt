package com.bangkit.mygithubapp.detail.followfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.core.data.Result
import com.bangkit.core.domain.model.User
import com.bangkit.mygithubapp.R
import com.bangkit.mygithubapp.databinding.FragmentFollowBinding
import com.bangkit.mygithubapp.detail.DetailViewModel
import com.bangkit.mygithubapp.detail.UserFollowAdapter
import com.bangkit.mygithubapp.util.showToast
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class FollowerFragment : Fragment() {

    private lateinit var binding: FragmentFollowBinding
    private val viewModel by activityViewModel<DetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObserver()
    }

    private fun setupObserver() {
        viewModel.userFollower.observe(viewLifecycleOwner) { result ->
            when(result) {
                is Result.Loading -> showLoading(true)
                is Result.Success -> {
                    showLoading(false)
                    setupRecyclerView(result.data)
                }
                is Result.Error -> {
                    showLoading(false)
                    showToast(result.error, requireContext())
                }
            }
        }
    }

    private fun setupRecyclerView(listUser: List<User>) {
        ifListEmpty(listUser.isEmpty())
        binding.rvUser.layoutManager = LinearLayoutManager(requireContext())
        binding.rvUser.adapter = UserFollowAdapter(listUser)
    }

    private fun ifListEmpty(isEmpty: Boolean) {
        binding.tvEmptyList.text = getString(R.string.empty_follower)
        binding.tvEmptyList.visibility = if (isEmpty) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private fun showLoading(showLoading: Boolean) {
        binding.pgLoading.visibility = if (showLoading) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}