package com.bangkit.mygithubapp.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bangkit.core.data.Result
import com.bangkit.core.domain.model.User
import com.bangkit.mygithubapp.R
import com.bangkit.mygithubapp.adapter.SectionPagerAdapter
import com.bangkit.mygithubapp.databinding.ActivityDetailBinding
import com.bangkit.mygithubapp.util.loadCircleImage
import com.bangkit.mygithubapp.util.showToast
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        setupObserver()
    }

    private fun setupObserver() {
        val username = intent.getStringExtra(EXTRA_USERNAME).orEmpty()

        viewModel.getDetailUser(username)
        viewModel.checkFavorite(username)

        viewModel.userDetail.observe(this) { result ->
            when(result) {
                is Result.Loading -> showLoading(true)
                is Result.Success -> {
                    showLoading(false)
                    setupView(result.data)
                    setupToolbar(result.data)
                }
                is Result.Error -> {
                    showLoading(false)
                    showToast(result.error, this)
                }
            }
        }

        viewModel.isFavorite.observe(this) { isFavorite ->
            this.isFavorite = isFavorite
            if (isFavorite) {
                binding.myToolbar.menu.findItem(R.id.favorite).setIcon(R.drawable.baseline_favorite_24)
            } else {
                binding.myToolbar.menu.findItem(R.id.favorite).setIcon(R.drawable.baseline_favorite_border_24)
            }
        }
    }

    private fun setupView(user: User) {
        setupTabLayout()
        binding.apply {
            ivProfile.loadCircleImage(user.avatar)
            tvUserID.text = user.username
            tvName.text = user.name
            tvFollowers.text = getString(R.string.follower_count, user.followers.toString())
            tvFollowing.text = getString(R.string.following_count, user.following.toString())
        }
    }

    private fun setupToolbar(user: User) {
        binding.apply {
            myToolbar.setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.favorite -> {
                        if (isFavorite) {
                            viewModel.removeFavorite(user.username)
                            viewModel.setFavorite(false)
                            showToast(getString(R.string.remove_favorite), this@DetailActivity)
                        } else {
                            viewModel.addFavorite(user)
                            viewModel.setFavorite(true)
                            showToast(getString(R.string.add_favorite), this@DetailActivity)
                        }
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun setupTabLayout() {
        val sectionsPagerAdapter = SectionPagerAdapter(this)
        binding.apply {
            viewPager.adapter = sectionsPagerAdapter
            TabLayoutMediator(tablayout, viewPager) {tabLayout, position ->
                tabLayout.text = TAB_TITLES[position]
            }.attach()
        }
    }

    private fun showLoading(show: Boolean) {
        binding.progressBar.visibility = if (show) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    companion object {
        const val EXTRA_USERNAME = "extra_username"
        private val TAB_TITLES = arrayOf("Followers", "Following")
    }
}