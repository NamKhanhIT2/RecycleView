package com.example.studentmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AddStudentActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var mssvEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        nameEditText = findViewById(R.id.editTextName)
        mssvEditText = findViewById(R.id.editTextMssv)
        saveButton = findViewById(R.id.buttonSave)
        cancelButton = findViewById(R.id.buttonCancel)

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val mssv = mssvEditText.text.toString()

            if (name.isNotEmpty() && mssv.isNotEmpty()) {
                val resultIntent = Intent()
                resultIntent.putExtra("name", name)
                resultIntent.putExtra("mssv", mssv)
                setResult(RESULT_OK, resultIntent)
                finish()
            } else {
                // Optionally show an error message
            }
        }

        cancelButton.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}