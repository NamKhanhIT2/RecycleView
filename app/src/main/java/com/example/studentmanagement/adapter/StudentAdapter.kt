package com.example.studentmanagement.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanagement.R
import com.example.studentmanagement.Student

class StudentAdapter(
    private val studentList: MutableList<Student>,
    private val onDeleteClick: (Int) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.textViewName)
        val mssvTextView: TextView = itemView.findViewById(R.id.textViewMssv)
        val deleteButton: View = itemView.findViewById(R.id.buttonDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val currentStudent = studentList[position]
        holder.nameTextView.text = currentStudent.name
        holder.mssvTextView.text = currentStudent.mssv
        holder.deleteButton.setOnClickListener {
            onDeleteClick(position)
        }
    }

    override fun getItemCount() = studentList.size

    fun addStudent(student: Student) {
        studentList.add(0, student)
        notifyItemInserted(0)
    }

    fun removeStudent(position: Int) {
        studentList.removeAt(position)
        notifyItemRemoved(position)
    }
}