package com.bangkit.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.favorite.databinding.ActivityFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.bangkit.core.domain.model.User
import com.bangkit.favorite.di.favoriteModule
import com.bangkit.mygithubapp.adapter.UserListAdapter
import com.bangkit.mygithubapp.detail.DetailActivity
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        setupObserver()
    }

    private fun setupObserver() {
        viewModel.getAllFavorite()
        viewModel.favoriteUser.observe(this) { listUser ->
            ifListEmpty(listUser.isEmpty())
            setupRecyclerView(listUser)
        }
    }

    private fun ifListEmpty(isEmpty: Boolean) {
        binding.tvEmptyList.visibility = if (isEmpty) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private fun setupRecyclerView(listUser: List<User>) {
        binding.rvUser.layoutManager = LinearLayoutManager(this)
        val adapter = UserListAdapter(listUser) {
            val intent = Intent(this@FavoriteActivity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_USERNAME, it.username)
            startActivity(intent)
        }
        binding.rvUser.adapter = adapter
    }
}