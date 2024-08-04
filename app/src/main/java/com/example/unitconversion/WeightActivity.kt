package com.example.unitconversion

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.unitconversion.databinding.ActivityWeightBinding

class WeightActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_weight)
private lateinit var binding: ActivityWeightBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeightBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val weightUnits = arrayOf("Gram", "Kilogram", "Pound", "Ounce")
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, weightUnits)
        binding.spinnerFrom.adapter = adapter
        binding.spinnerTo.adapter = adapter

        binding.btnConvert.setOnClickListener {
            convertWeight()
        }
    }

    private fun convertWeight() {
        val fromUnit = binding.spinnerFrom.selectedItem.toString()
        val toUnit = binding.spinnerTo.selectedItem.toString()
        val inputValue = binding.etAmount.text.toString()

        if (inputValue.isNotEmpty()) {
            val value = inputValue.toDouble()
            val result = when {
                fromUnit == "Gram" && toUnit == "Kilogram" -> value / 1_000
                fromUnit == "Kilogram" && toUnit == "Gram" -> value * 1_000
                fromUnit == "Pound" && toUnit == "Gram" -> value * 453.59237
                fromUnit == "Gram" && toUnit == "Pound" -> value / 453.59237
                fromUnit == "Pound" && toUnit == "Kilogram" -> value * 0.453592
                fromUnit == "Kilogram" && toUnit == "Pound" -> value / 0.453592
                fromUnit == "Ounce" && toUnit == "Kilogram" -> value * 0.0283495
                fromUnit == "Kilogram" && toUnit == "Ounce" -> value / 0.0283495
                fromUnit == "Gram" && toUnit == "Ounce" -> value / 28.3495231
                fromUnit == "Ounce" && toUnit == "Gram" -> value * 28.3495231
                fromUnit == "Ounce" && toUnit == "Pound" -> value / 16
                fromUnit == "Pound" && toUnit == "Ounce" -> value * 16
                else -> value // same unit
            }
            binding.tvConvertedAmount.text = result.toString()
        } else {
            Toast.makeText(this, "Please enter a value", Toast.LENGTH_SHORT).show()
        }
    }
    }
