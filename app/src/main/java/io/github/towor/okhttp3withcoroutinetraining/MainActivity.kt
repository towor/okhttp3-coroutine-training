package io.github.towor.okhttp3withcoroutinetraining

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import io.github.towor.okhttp3withcoroutinetraining.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        val weatherViewModel: WeatherViewModel by viewModels()

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.whetherViewModel = weatherViewModel
        binding.lifecycleOwner = this
    }
}
