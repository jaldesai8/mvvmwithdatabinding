package com.demo.databindingmvvm.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.demo.databindingmvvm.viewmodel.LogInHandler
import com.demo.databindingmvvm.viewmodel.LogInViewModel
import com.demo.databindingmvvm.R
import com.demo.databindingmvvm.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), LogInHandler {
    private lateinit var viewModel: LogInViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        this.viewModel = ViewModelProviders.of(this)[LogInViewModel::class.java]

        binding.logInModel = viewModel
        binding.handler = this

        viewModel.getLogInResult().observe(this, Observer { result ->
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
        })
        viewModel.getData().observe(this, Observer { result ->
            if(result){
                val intent = Intent(this, ImageActivity::class.java)
                startActivity(intent)
            }else{
                return@Observer
            }
        })
    }

    override fun onLogInClicked() {
        viewModel.performValidation()
    }
}