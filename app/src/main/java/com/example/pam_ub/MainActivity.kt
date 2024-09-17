package com.example.pam_ub

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var nilai1: EditText
    private lateinit var nilai2: EditText
    private lateinit var radiobutton: RadioGroup
    private lateinit var buttonHitung: Button
    private lateinit var hasilTextView: TextView
    private lateinit var currentEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        setupNumericButtons()
        setupCalculateButton()
    }

    private fun initializeViews() {
        nilai1 = findViewById(R.id.nilai1)
        nilai2 = findViewById(R.id.nilai2)
        radiobutton = findViewById(R.id.radiobutton)
        buttonHitung = findViewById(R.id.buttonhitung)
        hasilTextView = findViewById(R.id.textViewResult)
        currentEditText = nilai1 // Default focus on first EditText
    }

    private fun setupNumericButtons() {
        val numericButtons = arrayOf(
            R.id.button0,R.id.button1, R.id.button2, R.id.button3, R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9
        )

        numericButtons.forEach { buttonId ->
            findViewById<Button>(buttonId).setOnClickListener {
                val buttonText = (it as Button).text.toString()
                appendToCurrentEditText(buttonText)
            }
        }

        nilai1.setOnFocusChangeListener { _, hasFocus -> if (hasFocus) currentEditText = nilai1 }
        nilai2.setOnFocusChangeListener { _, hasFocus -> if (hasFocus) currentEditText = nilai2 }
    }

    private fun appendToCurrentEditText(text: String) {
        val currentText = currentEditText.text.toString()
        currentEditText.setText(currentText + text)
        currentEditText.setSelection(currentEditText.text.length) // Move cursor to end
    }

    private fun setupCalculateButton() {
        buttonHitung.setOnClickListener {
            calculateResult()
        }
    }

    private fun calculateResult() {
        val num1 = nilai1.text.toString().toDoubleOrNull()
        val num2 = nilai2.text.toString().toDoubleOrNull()

        if (num1 == null || num2 == null) {
            Toast.makeText(this, "Masukkan Angka dengan Tepat", Toast.LENGTH_SHORT).show()
            return
        }

        val result = when (radiobutton.checkedRadioButtonId) {
            R.id.radiotambah -> num1 + num2
            R.id.radiokurang -> num1 - num2
            R.id.radiokali -> num1 * num2
            R.id.radiobagi -> {
                if (num2 == 0.0) {
                    Toast.makeText(this, "Angka tidak bisa dibagi dengan nol", Toast.LENGTH_SHORT).show()
                    return
                }
                num1 / num2
            }
            else -> {
                Toast.makeText(this, "Pilih operasi bilangan", Toast.LENGTH_SHORT).show()
                return
            }
        }

        // Kirim hasil ke ResultActivity
        val intent = Intent(this, ResultActivity::class.java).apply {
            putExtra("RESULT", result)
            putExtra("NAME", "Nisrina Fatimah Parisya") // Ganti dengan nama yang sesuai
            putExtra("NIM", "225150407111071") // Ganti dengan NIM yang sesuai
        }
        startActivity(intent)
    }

}
