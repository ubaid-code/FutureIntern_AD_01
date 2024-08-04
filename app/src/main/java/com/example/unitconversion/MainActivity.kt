package com.example.unitconversion

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.unitconversion.databinding.ActivityMainActivityBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivTemperature.setOnClickListener {

            startActivity(Intent(this,TemperatureActivity::class.java))
        }

        binding.ivArea.setOnClickListener {
            startActivity(Intent(this, AreaActivity::class.java))
        }

        binding.ivLength.setOnClickListener {
            startActivity(Intent(this, LengthActivity::class.java))
        }

        binding.ivWeight.setOnClickListener {
            startActivity(Intent(this, WeightActivity::class.java))
        }
    }
}