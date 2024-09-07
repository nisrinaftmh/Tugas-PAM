package com.example.pam_ub

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val nilai1 = findViewById<EditText>(R.id.nilai1)
        val nilai2 = findViewById<EditText>(R.id.nilai2)
        val radiobutton = findViewById<RadioGroup>(R.id.radiobutton)
        val buttonCalculate =findViewById<Button>(R.id.buttonHitung)

        buttonCalculate.setOnClickListener {
            val num1 = nilai1.text.toString().toDoubleOrNull()
            val num2 = nilai2.text.toString().toDoubleOrNull()

            if (num1 == null || num2 == null) {
                Toast.makeText(this, "Masukkan Angka dengan Tepat", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val result = when (radiobutton.checkedRadioButtonId) {
                R.id.radiotambah -> num1 + num2
                R.id.radiokurang -> num1 - num2
                R.id.radiokali -> num1 * num2
                R.id.radiobagi -> {
                    if (num2 == 0.0) {
                        Toast.makeText(this, "Angka tidak bisa dibagi dengan nol", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                    num1 / num2
                }
                else -> {
                    Toast.makeText(this, "Pilih operasi bilangan", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }
            val intent = Intent(this, ResultActivity::class.java).apply {
                putExtra("RESULT", result)
            }
            startActivity(intent)
        }

    }
}