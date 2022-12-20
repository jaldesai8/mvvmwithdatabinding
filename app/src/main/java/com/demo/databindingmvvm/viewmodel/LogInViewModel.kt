package com.demo.databindingmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class LogInViewModel() : ViewModel() {

    var username: String = ""
    var password: String = ""

    private val logInResult = MutableLiveData<String>()
    private val isValid = MutableLiveData<Boolean>()

    fun getLogInResult(): LiveData<String> = logInResult

    fun getData() : LiveData<Boolean> = isValid

    fun performValidation() {
        if (username.isBlank()) {
            logInResult.value = "Invalid username"
            isValid.value = false
            return
        }
        if(password.isBlank()) {
            logInResult.value = "Invalid password"
            isValid.value = false
            return
        }
        logInResult.value = "Valid credentials :)"
        isValid.value = true
    }
}