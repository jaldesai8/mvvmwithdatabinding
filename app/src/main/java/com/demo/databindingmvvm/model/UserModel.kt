package com.demo.databindingmvvm.model

import android.util.Patterns




data class UserModel(
      var email : String,
      var password : String,
) {
      fun isEmailValid(): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
      }

      fun isPasswordLengthGreaterThan5(): Boolean {
            return password.length > 5
      }
}