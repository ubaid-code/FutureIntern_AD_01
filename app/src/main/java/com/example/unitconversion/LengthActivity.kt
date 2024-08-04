package com.example.unitconversion

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.unitconversion.databinding.ActivityLengthBinding

class LengthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLengthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLengthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lengthUnits = arrayOf("Meter", "Kilometer", "Centimeter", "Inch", "Foot")
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, lengthUnits)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerFrom.adapter = adapter
        binding.spinnerTo.adapter = adapter

        binding.btnConvert.setOnClickListener {
            convertLength()
        }
    }

    private fun convertLength() {
        val fromUnit = binding.spinnerFrom.selectedItem.toString()
        val toUnit = binding.spinnerTo.selectedItem.toString()
        val inputValue = binding.etAmount.text.toString()

        if (inputValue.isNotEmpty()) {
            val value = inputValue.toDouble()
            val result = when {
                fromUnit == "Meter" && toUnit == "Kilometer" -> value / 1_000
                fromUnit == "Kilometer" && toUnit == "Meter" -> value * 1_000
                fromUnit == "Meter" && toUnit == "Centimeter" -> value * 100
                fromUnit == "Centimeter" && toUnit == "Meter" -> value / 100
                fromUnit == "Meter" && toUnit == "Inch" -> value * 39.3701
                fromUnit == "Inch" && toUnit == "Meter" -> value / 39.3701
                fromUnit == "Inch" && toUnit == "Centimeter" -> value * 2.54
                fromUnit == "Centimeter" && toUnit == "Inch" -> value / 2.54
                fromUnit == "Meter" && toUnit == "Foot" -> value * 3.28084
                fromUnit == "Foot" && toUnit == "Meter" -> value / 3.28084
                fromUnit == "Foot" && toUnit == "Centimeter" -> value * 30.48
                fromUnit == "Centimeter" && toUnit == "Foot" -> value / 30.48
                fromUnit == "Foot" && toUnit == "Inch" -> value * 12
                fromUnit == "Inch" && toUnit == "Foot" -> value / 12
                fromUnit == "Foot" && toUnit == "Kilometer" -> value / 3280.8399
                fromUnit == "Kilometer" && toUnit == "Foot" -> value * 3280.8399
                fromUnit == "Kilometer" && toUnit == "Centimeter" -> value * 100000
                fromUnit == "Centimeter" && toUnit == "Kilometer" -> value / 100000
                fromUnit == "Kilometer" && toUnit == "Inch" -> value * 39370.0787
                fromUnit == "Inch" && toUnit == "Kilometer" -> value / 39370.0787
                else -> value // same unit
            }
            binding.tvConvertedAmount.text = result.toString()
        } else {
            Toast.makeText(this, "Please enter a value", Toast.LENGTH_SHORT).show()
        }
    }
}
