package com.example.unitconversion

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.example.unitconversion.databinding.ActivityAreaBinding

class AreaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAreaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAreaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val areaUnits = arrayOf("Square Meter", "Square Kilometer", "Hectare", "Acre")
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, areaUnits)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerFrom.adapter = adapter
        binding.spinnerTo.adapter = adapter

        binding.btnConvert.setOnClickListener {
            convertArea()
        }
    }
    private fun convertArea() {
        val fromUnit = binding.spinnerFrom.selectedItem.toString()
        val toUnit = binding.spinnerTo.selectedItem.toString()
        val inputValue = binding.etAmount.text.toString()

        if (inputValue.isNotEmpty()) {
            val value = inputValue.toDouble()
            val result = when {
                fromUnit == "Square Meter" && toUnit == "Square Kilometer" -> value / 1000000
                fromUnit == "Square Kilometer" && toUnit == "Square Meter" -> value * 1000000
                fromUnit == "Hectare" && toUnit == "Square Meter" -> value * 10000
                fromUnit == "Square Meter" && toUnit == "Hectare" -> value / 10000
                fromUnit == "Acre" && toUnit == "Square Meter" -> value * 4046.86
                fromUnit == "Square Meter" && toUnit == "Acre" -> value / 4046.86
                fromUnit == "Acre" && toUnit == "Hectare" -> value / 2.47105381
                fromUnit == "Hectare" && toUnit == "Acre" -> value * 2.47105381
                fromUnit == "Square Kilometer" && toUnit == "Hectare" -> value * 100
                fromUnit == "Hectare" && toUnit == "Square Kilometer" -> value / 100
                fromUnit == "Square Kilometer" && toUnit == "Acre" -> value * 247.105381
                fromUnit == "Acre" && toUnit == "Square Kilometer" -> value / 247.105381
                else -> value // same unit
            }
            binding.tvConvertedAmount.text = result.toString()
        } else {
            Toast.makeText(this, "Please enter a value", Toast.LENGTH_SHORT).show()
        }
    }
}