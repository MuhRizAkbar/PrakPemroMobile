package com.example.tipcalculatorxml

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.textfield.TextInputEditText
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var billAmountEditText: TextInputEditText
    private lateinit var tipPercentageAutoComplete: AutoCompleteTextView
    private lateinit var roundUpSwitch: MaterialSwitch
    private lateinit var tipResultTextView: TextView

    private val TIP_RESULT_KEY = "TIP_RESULT_KEY"
    private var currentCalculatedTip: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        billAmountEditText = findViewById(R.id.billAmountEditText)
        tipPercentageAutoComplete = findViewById(R.id.tipPercentageAutoComplete)
        roundUpSwitch = findViewById(R.id.roundUpSwitch)
        tipResultTextView = findViewById(R.id.tipResultTextView)

        val tipOptions = listOf("15%", "18%", "20%")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, tipOptions)
        tipPercentageAutoComplete.setAdapter(adapter)

        if (savedInstanceState != null) {
            currentCalculatedTip = savedInstanceState.getDouble(TIP_RESULT_KEY, 0.0)
            displayTip(currentCalculatedTip)
        }


        billAmountEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                calculateTip()
            }
        })

        tipPercentageAutoComplete.setOnItemClickListener { _, _, _, _ ->
            calculateTip()
        }

        roundUpSwitch.setOnCheckedChangeListener { _, _ ->
            calculateTip()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putDouble(TIP_RESULT_KEY, currentCalculatedTip)
    }

    private fun calculateTip() {
        val stringInTextField = billAmountEditText.text.toString()
        val cost = stringInTextField.toDoubleOrNull() ?: 0.0

        if (cost == 0.0) {
            currentCalculatedTip = 0.0
            displayTip(0.0)
            return
        }

        val stringInPercentageField = tipPercentageAutoComplete.text.toString()
        val tipPercentageStr = stringInPercentageField.replace("%", "")
        val tipPercentage = tipPercentageStr.toDoubleOrNull() ?: 15.0

        var tip = cost * (tipPercentage / 100)

        if (roundUpSwitch.isChecked) {
            tip = ceil(tip)
        }

        currentCalculatedTip = tip
        displayTip(tip)
    }

    private fun displayTip(tip: Double) {
        val formattedTip = NumberFormat.getCurrencyInstance(Locale.US).format(tip)
        tipResultTextView.text = getString(R.string.tip_amount, formattedTip)
    }
}