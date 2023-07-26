package com.bangkit.mygithubapp.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.core.data.Result
import com.bangkit.core.domain.model.User
import com.bangkit.core.domain.usecase.UserUseCase
import kotlinx.coroutines.launch

class HomeViewModel(private val useCase: UserUseCase) : ViewModel() {

    private val _listUser = MutableLiveData<Result<List<User>>>()
    val listUser: LiveData<Result<List<User>>> = _listUser

    fun getSearchUser(username: String)  {
        viewModelScope.launch {
            useCase.getUserByUsername(username).collect { result ->
                _listUser.value = result
            }
        }
    }
}