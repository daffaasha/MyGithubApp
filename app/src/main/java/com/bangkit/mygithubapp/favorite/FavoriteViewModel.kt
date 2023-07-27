package com.bangkit.mygithubapp.favorite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.core.domain.model.User
import com.bangkit.core.domain.usecase.UserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavoriteViewModel(private val useCase: UserUseCase) : ViewModel() {


    private val _favoriteUser = MutableLiveData<List<User>>()
    val favoriteUser: LiveData<List<User>> = _favoriteUser

    fun getAllFavorite() {
        viewModelScope.launch {
            useCase.getAllFavoriteUser().collect {
                _favoriteUser.value = it
            }
        }
    }

}