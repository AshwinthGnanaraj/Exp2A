package com.example.exp2a

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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

        val val1 = findViewById<EditText>(R.id.Value1)
        val val2 = findViewById<EditText>(R.id.Value2)
        val res = findViewById<EditText>(R.id.Result)

        val btnAdd = findViewById<Button>(R.id.add)
        val btnSub = findViewById<Button>(R.id.sub)
        val btnMul = findViewById<Button>(R.id.mul)
        val btnDiv = findViewById<Button>(R.id.div)
        val btnMod = findViewById<Button>(R.id.mod)

        fun calculate(operation: (Double, Double) -> Double) {
            val s1 = val1.text.toString()
            val s2 = val2.text.toString()

            if (s1.isEmpty() || s2.isEmpty()) {
                Toast.makeText(this, "Please enter both values", Toast.LENGTH_SHORT).show()
                return
            }

            val num1 = s1.toDouble()
            val num2 = s2.toDouble()
            
            val result = operation(num1, num2)
            res.setText(result.toString())
        }

        btnAdd.setOnClickListener { calculate { a, b -> a + b } }
        btnSub.setOnClickListener { calculate { a, b -> a - b } }
        btnMul.setOnClickListener { calculate { a, b -> a * b } }
        btnDiv.setOnClickListener {
            calculate { a, b ->
                if (b != 0.0) {
                    a / b
                } else {
                    Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show()
                    0.0
                }
            }
        }
        btnMod.setOnClickListener { calculate { a, b -> a % b } }
    }
}
