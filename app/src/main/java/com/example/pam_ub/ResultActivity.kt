package com.example.pam_ub

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // Mendapatkan data dari Intent
        val result = intent.getDoubleExtra("RESULT", 0.0)
        val name = intent.getStringExtra("NAME") ?: "Nama Tidak Diketahui"
        val nim = intent.getStringExtra("NIM") ?: "NIM Tidak Diketahui"

        // Menampilkan data ke TextView
        findViewById<TextView>(R.id.textViewResult).text = "Hasil: $result"
        findViewById<TextView>(R.id.textViewName).text = "Nama: $name"
        findViewById<TextView>(R.id.textViewNIM).text = "NIM: $nim"
    }
}
