package com.example.tiptime

import android.content.Context
import android.icu.text.NumberFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.tiptime.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            calculateTip()
        }
    }

    /**
     *  This method is used to calculate the tip
     */
    private fun calculateTip() {
        val stringInTextField = binding.costOfService.text.toString()
        if (stringInTextField == "") {
            Toast.makeText(this, "Please enter the input.", Toast.LENGTH_LONG).show()
        } else {
            val cost = stringInTextField.toDouble()
            val selectedId = binding.tipOption.checkedRadioButtonId
            val tipPercentage = when (selectedId) {
                R.id.option_twenty_percent -> 0.20
                R.id.option_eighteen_percent -> 0.18
                else -> 0.15
            }
            var tip = tipPercentage * cost
            val roundUp = binding.roundUpSwitch.isChecked
            if (roundUp) {
                tip = kotlin.math.ceil(tip)
            }
            val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
            binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
        }
    }

}