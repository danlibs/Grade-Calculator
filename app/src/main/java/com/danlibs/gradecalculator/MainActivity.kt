package com.danlibs.gradecalculator

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.danlibs.gradecalculator.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.lang.RuntimeException
import java.util.zip.DeflaterOutputStream

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            var grade1Double = 0.0
            var grade2Double = 0.0
            try {
                grade1Double = convertToDouble(binding.grade1)
                grade2Double = convertToDouble(binding.grade2)
            } catch (e: RuntimeException) {
                Snackbar.make(binding.studentStatus, "Erro inesperado", Snackbar.LENGTH_LONG).show()
            }
            val average = (grade1Double + grade2Double) / 2
            var faltas = 0
            try {
                faltas = Integer.parseInt(binding.faltas.text.toString())
            } catch (e: RuntimeException) {
                Snackbar.make(binding.studentStatus, "Erro inesperado", Snackbar.LENGTH_LONG).show()
            }
            if (average >= 7 && faltas < 11) {
                binding.studentStatus.setTextColor(Color.GREEN)
                binding.studentStatus.text =
                    getString(R.string.student_success).format(average, faltas)
            } else {
                binding.studentStatus.setTextColor(Color.RED)
                binding.studentStatus.text = getString(R.string.student_fail).format(average, faltas)
            }
        }
    }

    private fun convertToDouble(number: EditText): Double {
        val stringNumber = number.text.toString()
        return stringNumber.toDouble()
    }
}