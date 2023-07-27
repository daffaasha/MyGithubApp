package com.bangkit.mygithubapp.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.core.domain.model.User
import com.bangkit.core.domain.usecase.UserUseCase
import kotlinx.coroutines.launch
import com.bangkit.core.data.Result
import kotlinx.coroutines.Dispatchers

class DetailViewModel(private val useCase: UserUseCase) : ViewModel() {

    private val _userDetail = MutableLiveData<Result<User>>()
    val userDetail: LiveData<Result<User>> = _userDetail

    private val _userFollower = MutableLiveData<Result<List<User>>>()
    val userFollower: LiveData<Result<List<User>>> = _userFollower

    private val _userFollowing = MutableLiveData<Result<List<User>>>()
    val userFollowing: LiveData<Result<List<User>>> = _userFollowing

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    fun getDetailUser(userName: String) {
        viewModelScope.launch {
            useCase.getDetailUser(userName).collect {
                _userDetail.value = it
            }

            useCase.getUserFollowers(userName).collect {
                _userFollower.value = it
            }

            useCase.getUserFollowing(userName).collect {
                _userFollowing.value = it
            }
        }
    }

    fun checkFavorite(userName: String) {
        viewModelScope.launch {
            useCase.checkFavorite(userName).collect {
                _isFavorite.value = it.isEmpty()
            }
        }
    }

    fun setFavorite(isFavorite: Boolean) {
        _isFavorite.value = isFavorite
    }

    fun addFavorite(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.setFavorite(user)
        }
    }

    fun removeFavorite(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.deleteFavorite(username)
        }
    }
}