package com.example.studentmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanagement.adapter.StudentAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentAdapter
    private val studentList = mutableListOf<Student>()
    private lateinit var addButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewStudents)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initial dummy data
        studentList.add(Student("Nguyen Van A", "SV001"))
        studentList.add(Student("Tran Thi B", "SV002"))

        adapter = StudentAdapter(studentList) { position ->
            studentList.removeAt(position)
            adapter.notifyItemRemoved(position)
        }
        recyclerView.adapter = adapter

        addButton = findViewById(R.id.fabAddStudent)
        addButton.setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
            startActivityForResult(intent, ADD_STUDENT_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_STUDENT_REQUEST && resultCode == RESULT_OK) {
            val newName = data?.getStringExtra("name")
            val newMssv = data?.getStringExtra("mssv")
            if (!newName.isNullOrEmpty() && !newMssv.isNullOrEmpty()) {
                val newStudent = Student(newName, newMssv)
                adapter.addStudent(newStudent)
                recyclerView.scrollToPosition(0)
            }
        }
    }

    companion object {
        private const val ADD_STUDENT_REQUEST = 1
    }
}