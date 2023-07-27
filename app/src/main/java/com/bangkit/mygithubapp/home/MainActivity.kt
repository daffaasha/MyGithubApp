package com.bangkit.mygithubapp.home

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.core.data.Result
import com.bangkit.core.domain.model.User
import com.bangkit.mygithubapp.R
import com.bangkit.mygithubapp.adapter.UserListAdapter
import com.bangkit.mygithubapp.databinding.ActivityMainBinding
import com.bangkit.mygithubapp.detail.DetailActivity
import com.bangkit.mygithubapp.util.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
    }

    private fun moveToChatActivity() {
        try {
            startActivity(Intent(this, Class.forName("com.bangkit.favorite.FavoriteActivity")))
        } catch (e: Exception){
            showToast(getString(R.string.module_not_found), this)
        }

    }

    private fun setupObservers() {
        viewModel.listUser.observe(this) { result ->
            when (result) {
                is Result.Loading -> {
                    showLoading(true)
                }
                is Result.Success -> {
                    showLoading(false)
                    ifListEmpty(result.data.isEmpty())
                    setupRecyclerView(result.data)
                }
                is Result.Error -> {
                    showLoading(false)
                    showToast(result.error, this)
                }
            }
        }
    }
    private fun setupRecyclerView(listUser: List<User>) {
        binding.rvUser.layoutManager = LinearLayoutManager(this)
        val adapter = UserListAdapter(listUser) {
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_USERNAME, it.username)
            startActivity(intent)
        }
        binding.rvUser.adapter = adapter
    }

    private fun showLoading(show: Boolean) {
        binding.pgLoading.visibility = if (show) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private fun ifListEmpty(isEmpty: Boolean) {
        binding.tvEmptyList.visibility = if (isEmpty) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private fun moveToFavoriteActivity() {
        startActivity(Intent(this, Class.forName("com.bangkit.mygithubapp.favorite.FavoriteActivity")))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = "Input Username"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.getSearchUser(query)
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite -> {
                moveToChatActivity()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}