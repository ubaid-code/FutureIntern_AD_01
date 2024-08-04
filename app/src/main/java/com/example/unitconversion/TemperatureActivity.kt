package com.example.unitconversion

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.unitconversion.databinding.ActivityTemperatureBinding

class TemperatureActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTemperatureBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTemperatureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val temperatureUnits = arrayOf("Celsius", "Fahrenheit", "Kelvin")
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, temperatureUnits)
        binding.spinnerFrom.adapter = adapter
        binding.spinnerTo.adapter = adapter

        binding.btnConvert.setOnClickListener {
            convertTemperature()
        }
    }

    private fun convertTemperature() {
        val fromUnit = binding.spinnerFrom.selectedItem.toString()
        val toUnit = binding.spinnerTo.selectedItem.toString()
        val inputValue = binding.etAmount.text.toString()

        if (inputValue.isNotEmpty()) {
            val value = inputValue.toDouble()
            val result = when {
                fromUnit == "Celsius" && toUnit == "Fahrenheit" -> (value * 9 / 5) + 32
                fromUnit == "Fahrenheit" && toUnit == "Celsius" -> (value - 32) * 5 / 9
                fromUnit == "Celsius" && toUnit == "Kelvin" -> value + 273.15
                fromUnit == "Kelvin" && toUnit == "Celsius" -> value - 273.15
                fromUnit == "Fahrenheit" && toUnit == "Kelvin" -> (value - 32) * 5 / 9 + 273.15
                fromUnit == "Kelvin" && toUnit == "Fahrenheit" -> (value - 273.15) * 9 / 5 + 32
                else -> value // same unit
            }
            binding.tvConvertedAmount.text = result.toString()
        } else {
            Toast.makeText(this, "Please enter a value", Toast.LENGTH_SHORT).show()
        }
    }
}